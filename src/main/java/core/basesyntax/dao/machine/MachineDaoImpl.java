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
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();

            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException("Failed to persist entity: " + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int productionYear = LocalDate.now().minusYears(age).getYear();
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("from Machine"
                    + " where year < :value", Machine.class);
            query.setParameter("value", productionYear);

            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to select machine by age: " + age, e);
        }
    }
}
