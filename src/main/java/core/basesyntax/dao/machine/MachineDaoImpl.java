package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
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
        Transaction transaction = null;
        Session session = null;
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
            throw new RuntimeException("Can't insert in DB a machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int maxYear = LocalDate.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine "
                    + "WHERE year < :maxYear", Machine.class);
            query.setParameter("maxYear", maxYear);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find in DB the machines with older than "
                    + age + "produced before year " + maxYear, e);
        }
    }
}
