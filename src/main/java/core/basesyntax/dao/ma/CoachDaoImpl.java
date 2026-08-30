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
        try (org.hibernate.Session session = sessionFactory.openSession()) {
            return session.createQuery("from coaches c where c.experience > :years")
                    .setParameter("years", years)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach by experience", e);
        }
    }
}
