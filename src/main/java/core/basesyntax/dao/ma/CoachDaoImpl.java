package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            HibernateCriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> query = criteriaBuilder.createQuery(Coach.class);
            query.from(Coach.class);
            return session.createQuery(query).getResultList()
                    .stream()
                    .filter(coach -> coach.getExperience() > years)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach by experience greater than " + years, e);
        }
    }
}
