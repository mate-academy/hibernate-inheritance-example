package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import java.util.NoSuchElementException;
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
            Query<Coach> getCoachByExperienceGreaterThanQuery =
                    session.createQuery("FROM Coach AS c "
                            + "WHERE c.experience > :age", Coach.class);
            getCoachByExperienceGreaterThanQuery.setParameter("age", years);
            return getCoachByExperienceGreaterThanQuery.getResultList();
        } catch (Exception e) {
            throw new NoSuchElementException("Can't get all coach greater than " + years, e);
        }
    }
}
