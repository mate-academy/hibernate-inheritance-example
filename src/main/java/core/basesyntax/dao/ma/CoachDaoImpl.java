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
            Query<Coach> olderAge = session.createQuery("from Coach c "
                    + "where c.experience > :years", Coach.class);
            olderAge.setParameter("years", years);
            return olderAge.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find coach with greater "
                    + "experience than this years: " + years, e);
        }
    }
}
