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
            Query<Coach> query = null;

            query = session.createQuery("select c from Coach as c "
                    + "where c.experience >" + years, Coach.class);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get animals with first letter: " + years, e);
        }
    }
}
