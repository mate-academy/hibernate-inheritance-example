package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    private static final String EXPERIENCE_PARAMETER = "experience";

    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Coach c "
                                    + "WHERE c.experience > :" + EXPERIENCE_PARAMETER, Coach.class)
                    .setParameter(EXPERIENCE_PARAMETER, years)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all coaches with experience greater than "
                    + years, e);
        }
    }
}
