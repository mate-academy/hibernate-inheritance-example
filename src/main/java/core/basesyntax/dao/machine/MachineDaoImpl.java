package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
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
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new NoSuchElementException("Can't insert to DB machine: "
                    + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> getMachineByAgeOlderThanQuery =
                    session.createQuery("FROM Machine AS m "
                            + "WHERE m.year < :age", Machine.class);
            LocalDate now = LocalDate.now();
            int year = now.getYear();
            int machineYear = year - age;
            getMachineByAgeOlderThanQuery.setParameter("age", machineYear);
            return getMachineByAgeOlderThanQuery.getResultList();
        } catch (Exception e) {
            throw new NoSuchElementException("Can't get all machine older than " + age, e);
        }
    }
}
