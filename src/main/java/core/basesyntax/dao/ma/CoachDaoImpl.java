package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
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
            var coachQuery = session.createQuery("FROM Coach c "
                    + "WHERE c.experience > :years", Coach.class);
            coachQuery.setParameter("years", years);
            return coachQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find coach with experience greater than: "
                    + years, e);
        }
    }
}
