package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
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
            String queryString = "FROM Coach m "
                    + "WHERE m.experience > :years";
            Query<Coach> coachQuery = session.createQuery(queryString, Coach.class);
            coachQuery.setParameter("years", years);
            return coachQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get coach "
                    + "with experience greater than given: "
                    + years + " years", e);
        }
    }
}
