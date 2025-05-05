package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save machine " + machine, e);
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
            int year = Year.now().getValue();
            int yearOfCar = year - age;
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Machine> machineCriteriaQuery = cb.createQuery(Machine.class);
            Root<Machine> root = machineCriteriaQuery.from(Machine.class);
            machineCriteriaQuery.where(cb.lessThan(root.get("year"), yearOfCar));
            Query<Machine> sessionQuery = session.createQuery(machineCriteriaQuery);
            return sessionQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find machine by age " + age, e);
        }
    }
}
