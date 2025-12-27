package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
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
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't save machine " + machine.getMaker());
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = LocalDateTime.now().getYear();
        int targetYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "from Machine m where m.year < :targetYear", Machine.class)
                    .setParameter("targetYear", targetYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't find a machine older than " + age, e);
        }
    }
}
