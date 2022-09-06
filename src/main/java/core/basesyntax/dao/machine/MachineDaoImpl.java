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
            return machine;
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save machine " + machine + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> findMachineByAgeQuery = session.createQuery("FROM Machine m "
                    + "WHERE m.year < :yearOfManufacture", Machine.class);
            int beforeYearOfManufacture = LocalDate.now().getYear() - age;
            findMachineByAgeQuery.setParameter("yearOfManufacture", beforeYearOfManufacture);
            return findMachineByAgeQuery.getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("I can't find machine older than age: " + age, e);
        }
    }
}
