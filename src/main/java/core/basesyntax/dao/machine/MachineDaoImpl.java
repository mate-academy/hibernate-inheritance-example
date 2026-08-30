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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save machine: " + machine, e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        List<Machine> result = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String query = "FROM Machine m WHERE YEAR(CURRENT_DATE) - m.year > :age";
            result = session.createQuery(query, Machine.class)
                    .setParameter("age", age)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find machine older than " + age, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
