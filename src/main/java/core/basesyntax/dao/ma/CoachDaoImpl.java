package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
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
            CriteriaQuery<Coach> criteriaQuery =
                    criteriaBuilder.createQuery(Coach.class);
            Root<Coach> root = criteriaQuery.from(Coach.class);
            Predicate experiencePredicate = criteriaBuilder.greaterThan(root.get("experience"),
                    years);
            criteriaQuery.select(root).where(experiencePredicate);
            List<Coach> result = session.createQuery(criteriaQuery).getResultList();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach experience greater than"
                    + years, e);
        }
    }
}
