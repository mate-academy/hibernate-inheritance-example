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
            String findByExperienceGreaterThan = "from Coach where experience > :years";
            Query<Coach> query = session.createQuery(findByExperienceGreaterThan, Coach.class);
            query.setParameter("years", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error while finding Coach", e);
        }
    }
}
