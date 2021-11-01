package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
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
            session.save(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't add machine: "
                    + machine + " to the DB. ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getAllMachineQuery = session.createQuery(
                    "FROM Machine m WHERE "
                            + "EXTRACT(YEAR FROM CURRENT_DATE) - m.year > :age", Machine.class);
            getAllMachineQuery.setParameter("age", age);
            return getAllMachineQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get all machines by age: " + age, e);
        }
    }
}
