package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
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
            throw new RuntimeException("Can't insert a machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int year = LocalDateTime.now().getYear();
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Machine WHERE :currentYear - year > :age";
            List<Machine> entities = session.createQuery(hql, Machine.class)
                    .setParameter("currentYear", year)
                    .setParameter("age", age)
                    .list();
            return entities;
        } catch (Exception e) {
            throw new RuntimeException("Can't get a machine by age older than: " + age, e);
        }
    }
}
