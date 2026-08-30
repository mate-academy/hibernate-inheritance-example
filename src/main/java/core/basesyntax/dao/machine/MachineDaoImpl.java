package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save machine: "
                    + machine + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            int currentYear = LocalDate.now().getYear();
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Machine> queryForMachine = cb.createQuery(Machine.class);
            Root<Machine> machineRoot = queryForMachine.from(Machine.class);
            Predicate yearPredicate = cb.lt(machineRoot.get("year"), currentYear - age);
            queryForMachine.where(yearPredicate);
            return session.createQuery(queryForMachine).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find machine older than: "
                    + age, e);
        }
    }
}
