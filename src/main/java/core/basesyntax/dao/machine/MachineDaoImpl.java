package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.Year;
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
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(machine);
            tx.commit();
            return machine;
        } catch (Exception e) {
            if (tx != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(
                    "Error saving machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int year = Year.now().getValue() - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery(
                    "FROM Machine a WHERE a.year < :year", Machine.class);
            query.setParameter("year", year);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error finding machines by older age: " + age, e);
        }
    }
}
