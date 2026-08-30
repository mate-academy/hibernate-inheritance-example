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
            return session.createQuery("from Coach coach where coach.experience > :years",
                            Coach.class)
                    .setParameter("years", years)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find coach by experience", e);
        }
    }
}
