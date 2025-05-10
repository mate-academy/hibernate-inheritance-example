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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
            return machine;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error saving machine" + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        age = LocalDate.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            return session
                    .createQuery("FROM Machine WHERE year < :age", Machine.class)
                    .setParameter("age", age)
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error finding machine older than age" + age, e);
        }
    }
}
