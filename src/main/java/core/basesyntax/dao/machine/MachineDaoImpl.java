package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
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
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert machine - " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        List<Machine> machines = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            LocalDate localDate = LocalDate.now();
            int year = localDate.getYear();
            Query<Machine> queue = session.createQuery("from machines m "
                    + "where :year - m.year > :age", Machine.class);
            queue.setParameter("year", year);
            queue.setParameter("age", age);
            machines.addAll(queue.getResultList());
            return machines;
        } catch (HibernateException e) {
            throw new RuntimeException("Сan't get all machines older than year - " + age, e);
        }
    }
}
