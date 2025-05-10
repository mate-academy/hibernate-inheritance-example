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
            Query<Coach> queryOfCoach = session.createQuery("FROM Coach c "
                    + "WHERE c.experience > :years", Coach.class);
            queryOfCoach.setParameter("years", years);
            return queryOfCoach.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach with experience more than: "
                    + years, e);
        }
    }
}

