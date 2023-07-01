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
            Query<Coach> coachQuery = session.createQuery("FROM coach c "
                    + "WHERE experience > :experience", Coach.class);
            coachQuery.setParameter("experience", years);
            return coachQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Wow, can't find coach with experience greater than "
                    + years, e);
        }
    }
}
