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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rollbackEx) {
                    throw new DataProcessingException("Rollback failed.", rollbackEx);
                }
            }
            throw new DataProcessingException("Can`t save Machine to DB.", e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT m FROM Machine m WHERE m.year < :age", Machine.class)
                    .setParameter("age", LocalDate.now().getYear() - age)
                    .getResultList();
        }
    }
}
