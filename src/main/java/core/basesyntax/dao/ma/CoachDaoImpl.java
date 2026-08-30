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
            Query<Coach> getCoachQuery = session.createQuery("FROM Coach "
                    + "WHERE experience > :years", Coach.class);
            getCoachQuery.setParameter("years", years);
            return getCoachQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find coaches by experience greater than "
                    + years, e);
        }
    }
}
