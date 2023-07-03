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
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            throw new RuntimeException("Can't save machine " + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Machine m where m.year < :age", Machine.class)
                    .setParameter("age", LocalDate.now().getYear() - age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find machines older than " + age, e);
        }
    }
}
