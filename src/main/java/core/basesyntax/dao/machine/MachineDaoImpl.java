package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = java.time.Year.now().getValue();
        int targetYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            String query = "FROM Machine a WHERE a.year < :targetYear";
            return session.createQuery(query, Machine.class)
                    .setParameter("targetYear", targetYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find machines older than: " + age + " years", e);
        }
    }
}
