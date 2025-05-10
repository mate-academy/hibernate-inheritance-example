package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
            throw new RuntimeException("Can't insert machine " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = LocalDateTime.now().getYear();
        int expectedYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("from Machine m "
                    + "where m.year < :expected", Machine.class);
            query.setParameter("expected", expectedYear);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get machines which age is older than "
                    + age, e);
        }
    }
}
