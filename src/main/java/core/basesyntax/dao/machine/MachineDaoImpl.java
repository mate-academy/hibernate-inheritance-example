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
        Session session = null;
        Transaction tr = null;
        try {
            session = sessionFactory.openSession();
            tr = session.beginTransaction();
            session.persist(machine);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new RuntimeException("Cant save machine in DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = java.time.Year.now().getValue();
        int targetYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "from Machine m where m.year < :age", Machine.class)
                    .setParameter("age", targetYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cant find machines older than age: " + age, e);
        }
    }
}
