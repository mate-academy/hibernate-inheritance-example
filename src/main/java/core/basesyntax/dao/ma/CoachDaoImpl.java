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
            Query<Coach> getAllCoach = session.createQuery("FROM Coach a WHERE "
                    + "a.experience > :year",Coach.class);
            getAllCoach.setParameter("year", years);
            return getAllCoach.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get a coach by years : " + years, e);
        }
    }
}
