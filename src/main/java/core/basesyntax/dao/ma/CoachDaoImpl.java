package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;

import core.basesyntax.model.ma.Mentor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> criteriaQuery = criteriaBuilder.createQuery(Coach.class);
            Root<Coach> root = criteriaQuery.from(Coach.class);
            criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("experience"), years));
            Query<Coach> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        }
    }
}
