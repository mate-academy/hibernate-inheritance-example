package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
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
            throw new RuntimeException("Can't insert a machine " + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();

            CriteriaQuery<Machine> query = criteriaBuilder.createQuery(Machine.class);
            Root<Machine> root = query.from(Machine.class);

            Expression<Integer> yearExp = root.get("year");
            Expression<Integer> ageExp = criteriaBuilder.diff(
                    criteriaBuilder.literal(LocalDate.now().getYear()), yearExp);

            query.select(root).where(criteriaBuilder.greaterThan(ageExp, age));

            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get a machine", e);
        }
    }
}
