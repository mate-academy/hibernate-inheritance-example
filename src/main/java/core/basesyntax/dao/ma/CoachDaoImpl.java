package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Coach;
import java.math.BigInteger;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        return new Random().nextInt(BigInteger.TWO.intValue())
                % BigInteger.TWO.intValue() == BigInteger.ZERO.intValue()
                ? findByExperienceGreaterThanHql(years)
                : findByExperienceGreaterThanCriteriaApi(years);
    }

    private List<Coach> findByExperienceGreaterThanHql(int years) {
        try (Session session = getSession()) {
            System.out.println("HQL is doing job");
            return session.createQuery("from Coach c "
                            + "where c.experience > :years",
                            Coach.class)
                    .setParameter("years", years)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find coach "
                            + "experience greater than %s", years), e);
        }
    }

    private List<Coach> findByExperienceGreaterThanCriteriaApi(int years) {
        try (Session session = getSession()) {
            System.out.println("Criteria API is doing job");
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Coach> query = cb.createQuery(Coach.class);
            Root<Coach> root = query.from(Coach.class);
            Predicate experiencePredicate = cb.gt(root.get("experience"), years);
            query.select(root).where(experiencePredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find coach "
                            + "experience greater than %s", years), e);
        }
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }
}
