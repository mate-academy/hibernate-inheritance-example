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
            throw new RuntimeException("Could not add Machine " + machine
                    + " to database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            int year = LocalDate.now().getYear() - age;
            Query<Machine> machineByAgeOlderThanQuery = session.createQuery(
                    "FROM Machine m "
                            + "WHERE m.year < :year", Machine.class
            );
            machineByAgeOlderThanQuery.setParameter("year", year);
            return machineByAgeOlderThanQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not find any Machine older than "
                    + age, e);
        }
    }
}
