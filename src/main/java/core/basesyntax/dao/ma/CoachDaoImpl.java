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
            return session.createQuery("FROM Coach c WHERE c.experience>:exp", Coach.class)
                    .setParameter("exp", years).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Unable to retrieve Mentors older than " + years, e);
        }
    }
}
