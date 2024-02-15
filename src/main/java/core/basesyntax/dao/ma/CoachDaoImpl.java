package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;

import core.basesyntax.model.ma.Mentor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Coach с WHERE с.experience > :years", Coach.class)
                    .setParameter("years", years)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Cannot find coaches with experience greater than: " + years, e);
        }
    }
}
