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
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(
                    "Can't save machine: " + machine + " , error: ", e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            int currentYear = Year.now().getValue();
            int thresholdYear = currentYear - age;

            List<Machine> result = session.createQuery(
                            "FROM Machine m WHERE m.year < "
                                    + ":thresholdYear", Machine.class)
                    .setParameter("thresholdYear", thresholdYear)
                    .getResultList();

            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(
                    "Can't find machines older than "
                            + age + " years, error: ", e);
        }
    }
}
