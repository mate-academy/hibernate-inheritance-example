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
            Query<Coach> getListOfCoachesByExperience = session.createQuery(
                    "SELECT c FROM Coach c "
                            + "WHERE c.experience > :required",
                    Coach.class);
            getListOfCoachesByExperience.setParameter("required", years);
            return getListOfCoachesByExperience.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find coach by experience: " + years);
        }
    }
}
