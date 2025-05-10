package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error adding machine: " + machine, e);
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int targetYear = LocalDate.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Machine a WHERE a.year < :targetYear", Machine.class)
                    .setParameter("targetYear", targetYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding machines older than age: " + age, e);
        }
    }
}
