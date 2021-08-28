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
            Query<Coach> findCoachesByExperienceGreaterThanQuery = session.createQuery(
                    "FROM Coach c WHERE c.experience > :experience", Coach.class);
            findCoachesByExperienceGreaterThanQuery.setParameter("experience", years);
            return findCoachesByExperienceGreaterThanQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get coaches with experience greater than "
                    + years + " years", e);
        }
    }
}
