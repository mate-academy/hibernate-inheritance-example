package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            session = getSession();
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    String.format("Can't save %s to DB", machine), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        return new Random().nextInt(1 << 1) % 2 == 0
                ? findByAgeOlderThanHql(age)
                : findByAgeOlderThanCriteriaApi(age);
    }

    private List<Machine> findByAgeOlderThanHql(int age) {
        try (Session session = getSession()) {
            System.out.println("Hello from HQL");
            return session.createQuery("from Machine m "
                                    + "where m.year < :age",
                            Machine.class)
                    .setParameter("age", LocalDateTime.now().getYear() - age)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find machine older than %s", age), e);
        }
    }

    private List<Machine> findByAgeOlderThanCriteriaApi(int age) {
        try (Session session = getSession()) {
            System.out.println("Hello from Criteria API");
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Machine> query = cb.createQuery(Machine.class);
            Root<Machine> machineRoot = query.from(Machine.class);
            Predicate yearPredicate = cb.lt(machineRoot.get("year"),
                    LocalDateTime.now().getYear() - age);
            query.select(machineRoot).where(yearPredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find machine older than %s", age), e);
        }
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }
}
