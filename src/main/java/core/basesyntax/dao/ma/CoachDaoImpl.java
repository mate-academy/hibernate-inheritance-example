package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    private static final String EXPERIENCE_PARAMETER = "experience";

    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> getMachineByAgeQuery =
                    session.createQuery("FROM Coach c "
                            + "WHERE c.experience > :" + EXPERIENCE_PARAMETER, Coach.class);
            getMachineByAgeQuery.setParameter(EXPERIENCE_PARAMETER, years);
            return getMachineByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a coach by experience greater than: "
                    + years + " years", e);
        }
    }
}
