package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
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
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
            return machine;
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(
                    String.format("Error saving machine %s", machine), ex
            );
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int yearToFind = LocalDate.now().minusYears(age).getYear();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Machine m WHERE m.year < :yearToFind", Machine.class)
                    .setParameter("yearToFind", yearToFind)
                    .getResultList();
        } catch (Exception ex) {
            throw new RuntimeException(
                    String.format("Error finding machine older than %d years", age), ex
            );
        }
    }
}
