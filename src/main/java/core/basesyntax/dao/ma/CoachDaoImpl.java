package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    private final SessionFactory sessionFactory;

    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Coach c WHERE c.experience > :year", Coach.class)
                    .setParameter("year", years).list();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Couldn't find coach with experience greater than: " + years, e);
        }
    }
}
