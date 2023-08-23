package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;

import java.time.LocalDate;
import java.util.List;

import core.basesyntax.model.zoo.Animal;
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
            throw new RuntimeException("Cannot add machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Machine m "
                        + "WHERE m.year < :age", Machine.class)
                    .setParameter("age", LocalDate.now().getYear() - age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get machine older than: " + age);
        }
    }
}
