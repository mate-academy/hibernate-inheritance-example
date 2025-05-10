package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    private static final String MACHINE_AGE_PARAM = "age";
    private static final String CURRENT_YEAR_PARAM = "currentYear";

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
            throw new DataProcessingException("Can't insert a machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = LocalDate.now().getYear();
        String hql = "FROM Machine m WHERE :" + CURRENT_YEAR_PARAM
                + " - m.year "
                + "> :" + MACHINE_AGE_PARAM;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getMachinesByAge = session.createQuery(hql, Machine.class);
            getMachinesByAge.setParameter(CURRENT_YEAR_PARAM, currentYear);
            getMachinesByAge.setParameter(MACHINE_AGE_PARAM, age);
            return getMachinesByAge.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any machine "
                                                + "with age older than: " + age, e);
        }
    }
}
