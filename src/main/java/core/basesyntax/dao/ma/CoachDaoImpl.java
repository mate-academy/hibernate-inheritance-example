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
        List<Coach> result = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String query = "FROM Coach c WHERE c.experience > :years";
            result = session.createQuery(query, Coach.class)
                    .setParameter("years", years)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find coach with greater experience ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
