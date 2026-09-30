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
        } catch (RuntimeException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save machine", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int yearLimit = Year.now().getValue() - age;

        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery(
                    "from Machine m where m.year < :yearLimit", Machine.class);
            query.setParameter("yearLimit", yearLimit);
            return query.getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can't find machine by age", e);
        }
    }
}
