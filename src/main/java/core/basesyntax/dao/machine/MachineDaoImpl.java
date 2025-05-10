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
            throw new RuntimeException("Cannot save a machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = LocalDate.now().getYear();
        int thresholdYear = currentYear - age;

        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getMachinesByAge = session.createQuery(
                    "SELECT m FROM Machine m "
                            + "WHERE  m.year < :thresholdYear",
                    Machine.class);
            getMachinesByAge.setParameter("thresholdYear", thresholdYear);
            return getMachinesByAge.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get machine by age: " + age, e);
        }
    }
}
