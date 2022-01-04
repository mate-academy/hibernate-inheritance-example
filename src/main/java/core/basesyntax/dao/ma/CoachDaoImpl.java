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
            Query<Coach> query = session.createQuery("FROM Coach a "
                    + "WHERE experience > :experience", Coach.class);
            query.setParameter("experience", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach with experience greater than "
                    + years + " years.", e);
        }
    }
}
