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
            Query<Coach> findByExperienceQuery = session.createQuery("from Coach c "
                    + "where c.experience > :years", Coach.class);
            findByExperienceQuery.setParameter("years", years);
            return findByExperienceQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get from DB coaches "
                    + "with experience greater than:" + years);
        }
    }
}
