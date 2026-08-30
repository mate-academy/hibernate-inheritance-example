package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try {
            return sessionFactory.fromSession(s -> s.createQuery("FROM Coach "
                            + "WHERE experience > :years", Coach.class)
                    .setParameter("years", years)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("No coaches with more than " + years
                    + " experiences", e);
        }
    }
}
