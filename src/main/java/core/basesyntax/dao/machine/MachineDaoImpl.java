package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.util.HibernateUtil;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Optional<Machine> getId(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return Optional.ofNullable(session.get(Machine.class, id));
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a machine by id: " + id, e);
        }
    }

    @Override
    public Machine save(Machine machine) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert an machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine m "
                    + "WHERE year< :years", Machine.class);
            query.setParameter("years", LocalDate.now().getYear() - age);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find machine by year greater than: "
                    + age, e);
        }
    }
}
