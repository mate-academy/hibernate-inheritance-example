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
            throw new RuntimeException("Can`t add machine " + machine + " to the DB", e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int targetYear = LocalDate.now().getYear() - age;
        String query = "FROM Machine m WHERE m.year < :targetYear";
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> machineQuery = session.createQuery(query);
            machineQuery.setParameter("targetYear", targetYear);
            return machineQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t find machines older than " + age, e);
        }
    }
}
