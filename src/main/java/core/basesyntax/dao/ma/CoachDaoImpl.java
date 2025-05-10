package core.basesyntax.dao.ma;

import core.basesyntax.DataProcessingException;
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
            Query<Coach> query = session.createQuery("FROM Coach c WHERE c.experience > :years",
                    Coach.class);
            return query.setParameter("years", years).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any coach with this experience or more: "
                    + years, e);
        }
    }
}
