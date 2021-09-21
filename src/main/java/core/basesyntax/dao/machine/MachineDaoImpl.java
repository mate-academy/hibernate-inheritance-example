package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
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
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
        } catch (DataProcessingException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add machine to DB!");
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
            int currentYear = LocalDate.now().getYear();
            int year = currentYear - age;
            Query<Machine> query = session.createQuery("FROM Machine m "
                    + "WHERE m.year < :year", Machine.class);
            query.setParameter("year", year);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find machine older than: " + age, e);
        }
    }
}
