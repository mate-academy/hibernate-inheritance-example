package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.HibernateException;
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
            Query<Coach> getCoachByAgeGreaterThan =
                    session.createQuery("from Coach where experience > :years", Coach.class);
            getCoachByAgeGreaterThan.setParameter("years", years);
            return getCoachByAgeGreaterThan.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't get list of coaches where age greater than "
                    + years, e);
        }
    }
}
