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
        String findRequest = "FROM Coach c WHERE c.experience > :years";
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> query = session.createQuery(findRequest, Coach.class);
            query.setParameter("years", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coaches by experience: " + years, e);
        }
    }
}
