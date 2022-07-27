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
            session.save(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot save " + machine + " in DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int yearLimit = LocalDate.now().minusYears(2).getYear();
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery(
                    "from Machine m WHERE m.year < :yearProduction", Machine.class);
            query.setParameter("yearProduction", yearLimit);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get list of machines from DB by age=" + age);
        }
    }
}
