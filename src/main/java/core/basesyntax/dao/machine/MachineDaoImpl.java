package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.Year;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    private static final String AGE_PARAMETER = "age";

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
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getMachineByAgeQuery =
                    session.createQuery("FROM Machine m "
                            + "WHERE m.year < :" + AGE_PARAMETER, Machine.class);
            getMachineByAgeQuery.setParameter(AGE_PARAMETER, Year.now().minusYears(age).getValue());
            return getMachineByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a machine by age older than: " + age, e);
        }
    }
}
