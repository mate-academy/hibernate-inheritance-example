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
            Query<Coach> coachByExperienceGreaterThanQuery = session.createQuery(
                    "FROM Coach c "
                            + "WHERE c.experience > :years", Coach.class
            );
            coachByExperienceGreaterThanQuery.setParameter("years", years);
            return coachByExperienceGreaterThanQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not find any Coach with experience greater than "
                    + years, e);
        }
    }
}
