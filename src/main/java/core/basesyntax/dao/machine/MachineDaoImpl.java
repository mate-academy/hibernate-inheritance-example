package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert a machine: " + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int yearOfMachineProduction = LocalDateTime.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine m "
                    + "WHERE m.year < :year", Machine.class);
            query.setParameter("year", yearOfMachineProduction);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a machine older than: " + age, e);
        }
    }
}
