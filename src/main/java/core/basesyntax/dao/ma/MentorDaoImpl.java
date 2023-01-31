package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        return new Random().ints()
                .limit(BigInteger.ONE.intValue())
                .anyMatch(n -> n % (1 << 1) == 0)
                ? findByAgeGreaterThanHql(age)
                : findByAgeGreaterThanCriteriaApi(age);
    }

    private List<Mentor> findByAgeGreaterThanHql(int age) {
        try (Session session = getSession()) {
            System.out.println("HQL in deal");
            return session.createQuery("from Mentor m "
                            + "where m.age > :age", Mentor.class)
                    .setParameter("age", age)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find mentor older than %s", age), e);
        }
    }

    private List<Mentor> findByAgeGreaterThanCriteriaApi(int age) {
        try (Session session = getSession()) {
            System.out.println("Criteria API in deal");
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> query = cb.createQuery(Mentor.class);
            Root<Mentor> root = query.from(Mentor.class);
            Predicate agePredicate = cb.gt(root.get("age"), age);
            query.select(root).where(agePredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find mentor older than %s", age), e);
        }
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }
}
