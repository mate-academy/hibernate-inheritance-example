package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Coach> coachQuery = cb.createQuery(Coach.class);
            Root<Coach> root = coachQuery.from(Coach.class);
            coachQuery.where(cb.greaterThan(root.get("experience"), years));
            Query<Coach> sessionQuery = session.createQuery(coachQuery);
            return sessionQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach by experience " + years);
        }
    }
}
