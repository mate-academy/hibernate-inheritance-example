package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
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
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> coachCriteriaQuery =
                    criteriaBuilder.createQuery(Coach.class);
            Root<Coach> coachRoot =
                    coachCriteriaQuery.from(Coach.class);
            Predicate predicate =
                    criteriaBuilder.gt(coachRoot.get("experience"), years);
            coachCriteriaQuery.select(coachRoot).where(predicate);
            return session.createQuery(coachCriteriaQuery.distinct(true)).getResultList();
        }
    }
}
