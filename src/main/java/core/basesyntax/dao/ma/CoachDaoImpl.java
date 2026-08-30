package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Coach> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Coach.class);
            criteriaQuery.from(Coach.class);
            return session.createQuery(criteriaQuery).getResultList().stream()
                    .filter(c -> c.getExperience() > years)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Can't find all coaches with experience more then: "
                    + years + " years", e);
        }

    }
}
