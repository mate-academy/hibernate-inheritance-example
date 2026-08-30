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
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(machine);
            tx.commit();
            return machine;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Couldn't add machine = " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = java.time.Year.now().getValue();
        int thresholdYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("from Machine m "
                    + "where m.year < :age", Machine.class);
            query.setParameter("age", thresholdYear);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't find machines older than age = " + age, e);
        }
    }
}
