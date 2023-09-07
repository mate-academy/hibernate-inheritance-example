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
            throw new RuntimeException("Can`t save machine: " + machine);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int yearAge = LocalDate.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getByAgeOlderThanQuery = session.createQuery("FROM Machine m "
                    + "WHERE m.year < :age");
            getByAgeOlderThanQuery.setParameter("age", yearAge);
            return getByAgeOlderThanQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t find machine older than: " + age, e);
        }
    }
}
