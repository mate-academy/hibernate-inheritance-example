package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import core.basesyntax.util.HibernateUtil;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Coach> query = session.createQuery("FROM Coach c " + "WHERE c.experience > :year",
                    Coach.class);
            query.setParameter("year", years);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach by year: " + years, e);
        }
    }
}
