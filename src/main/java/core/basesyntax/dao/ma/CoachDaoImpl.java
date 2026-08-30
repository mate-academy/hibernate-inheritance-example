package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Coach WHERE experience > :wantedExperience";
            return session.createQuery(hql, Coach.class)
                    .setParameter("wantedExperience", years)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not find coaches with experience greater than: "
                    + years, e);
        }
    }
}
