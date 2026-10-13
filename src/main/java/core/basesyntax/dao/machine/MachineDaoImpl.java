package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
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
        Session session = null;
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
            throw new DataProcessingException("Can't insert machine " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = LocalDate.now().getYear();
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery(
                            "from Machine m "
                                    + "WHERE :currentYear - m.year > :age ",
                            Machine.class)
                    .setParameter("currentYear", currentYear)
                    .setParameter("age", age)
                    .list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any "
                    + Machine.class.getSimpleName() + " older than "
                    + age, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
