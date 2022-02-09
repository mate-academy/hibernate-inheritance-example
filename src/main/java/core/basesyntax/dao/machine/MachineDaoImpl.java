package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
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
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save machine" + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        /*
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Machine> query = cb.createQuery(Machine.class);
            Root<Machine> machineRoot = query.from(Machine.class);
            Predicate ageLt = cb.lt(machineRoot.get("year"), age);
            query.where(ageLt);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find machine older then " + age, e);
        }
         */
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine WHERE year < :year",
                    Machine.class);
            return query.setParameter("year", LocalDateTime.now()
                    .getYear() - age).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find machine alder than " + age);
        }
    }
}
