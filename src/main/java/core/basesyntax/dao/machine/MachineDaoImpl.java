package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.Year;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(
                    "Can't save machine: " + machine + " , error: ", e);
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = Year.now().getValue();
        int maxYear = currentYear - age;

        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Machine m WHERE m.year < :maxYear", Machine.class)
                    .setParameter("maxYear", maxYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't find machine older than: " + age + " , error: ", e);
        }
    }
}
