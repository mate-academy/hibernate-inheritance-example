package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.Year;
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
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error occurred while inserting machine "
                    + "into the database: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int currentYear = Year.now().getValue();
        int maxYear = currentYear - age;
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Machine WHERE year < :maxYear";
            return session.createQuery(hql, Machine.class)
                    .setParameter("maxYear", maxYear)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Error occurred while retrieving machines "
                    + "from the database", e);
        }
    }
}
