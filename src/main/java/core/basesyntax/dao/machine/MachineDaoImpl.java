package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
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
        Session session = null;
        Transaction transaction = null;

        try {
            session = this.sessionFactory.openSession();
            transaction = session.beginTransaction();

            session.persist(machine);
            transaction.commit();

            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Failed to save machine " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int maxYearForAge = LocalDate.now().getYear() - age;
        try (Session session = this.sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Machine m WHERE m.year < :maxYearForAge", Machine.class)
                    .setParameter("maxYearForAge", maxYearForAge)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get machines with age greater than " + age, e);
        }
    }
}
