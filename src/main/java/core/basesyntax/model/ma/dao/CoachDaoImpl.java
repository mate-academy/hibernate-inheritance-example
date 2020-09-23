package core.basesyntax.model.ma.dao;

import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> criteriaQuery
                    = criteriaBuilder.createQuery(Coach.class);
            Root<Coach> root = criteriaQuery.from(Coach.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.greaterThan(root.get("experience"), years)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving coaches", e);
        }
    }
}
