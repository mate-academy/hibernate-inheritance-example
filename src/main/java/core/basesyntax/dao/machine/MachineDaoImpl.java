package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.Year;
import java.util.ArrayList;
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
            throw new RuntimeException("Can't save machine " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = Year.now().getValue();
        int maxYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            List<Machine> result = new ArrayList<>();
            result.addAll(session.createQuery("FROM Car c WHERE c.year < :maxYear", Machine.class)
                    .setParameter("maxYear", maxYear)
                    .getResultList());
            result.addAll(session.createQuery("FROM Truck t WHERE t.year < :maxYear", Machine.class)
                    .setParameter("maxYear", maxYear)
                    .getResultList());
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Can't find machines older than " + age + " years", e);
        }
    }
}
