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
            Query<Coach> getAllFilteredCoaches =
                    session.createQuery("from Coach c where c.experience > :y", Coach.class);
            getAllFilteredCoaches.setParameter("y", years);
            return getAllFilteredCoaches.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't retrieve coaches from db.", e);
        }
    }
}
