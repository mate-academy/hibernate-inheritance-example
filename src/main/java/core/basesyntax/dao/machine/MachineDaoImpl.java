package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDateTime;
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
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save to DB machine " + machine);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int minYear = LocalDateTime.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Machine> query = criteriaBuilder.createQuery(Machine.class);
            Root<Machine> root = query.from(Machine.class);
            Predicate predicate = criteriaBuilder.lt(root.get("year"),minYear);
            query.where(predicate);
            return session.createQuery(query).getResultList();
        }
    }
}
