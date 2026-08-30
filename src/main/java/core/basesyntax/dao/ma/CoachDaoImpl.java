package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {

    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        if (years < 0) {
            throw new IllegalArgumentException("Experience cannot be negative");
        }
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            var query = session.createQuery(
                    "FROM Coach e WHERE e.experience > :years",
                    Coach.class);
            query.setParameter("years", years);
            List<Coach> result = query.getResultList();
            tx.commit();
            return result;
        }
    }
}
