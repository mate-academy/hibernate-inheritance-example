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
            throw new RuntimeException("Cannot add machine to database: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int targetYear = LocalDate.now().getYear() - age;

        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getAllFilteredMachines =
                    session.createQuery("from Machine m where m.year < :targetYear", Machine.class);
            getAllFilteredMachines.setParameter("targetYear", targetYear);
            return getAllFilteredMachines.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't retrieve machines from db.", e);
        }
    }
}
