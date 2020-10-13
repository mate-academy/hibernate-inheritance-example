package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
            CriteriaQuery<Coach> query = criteriaBuilder.createQuery(Coach.class);
            Root<Coach> root = query.from(Coach.class);
            query.where(criteriaBuilder.greaterThan(root.get("experience"), years));
            List<Coach> resultList = session.createQuery(query).getResultList();
            return resultList;
        }
    }
}
