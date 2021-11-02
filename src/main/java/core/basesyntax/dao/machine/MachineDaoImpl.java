package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.Query;
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
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save machine " + machine
                    + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query findByAgeQuery =
                    session.createQuery("FROM Machine "
                            + "WHERE year < :year");
            findByAgeQuery.setParameter("year", LocalDate.now().minusYears(age).getYear());
            return findByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get machines older than: " + age, e);
        }
    }
}
