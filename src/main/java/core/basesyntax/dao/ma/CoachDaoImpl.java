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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Coach> cq = cb.createQuery(Coach.class);
            Root<Coach> rootCoach = cq.from(Coach.class);

            Predicate getCoachByExperienceGreaterThan =
                    cb.greaterThan(rootCoach.get("experience"), years);

            cq.select(rootCoach).where(getCoachByExperienceGreaterThan);
            return session.createQuery(cq).getResultList();
        }
    }
}
