package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Coach "
                            + "WHERE experience > :years", Coach.class)
                    .setParameter("years", years)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get coaches by experience greater than " + years, e);
        }
    }
}
