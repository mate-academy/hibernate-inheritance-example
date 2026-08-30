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
            String hql = "FROM Coach WHERE experience > :entityYear";
            List<Coach> entities = session.createQuery(hql, Coach.class)
                    .setParameter("entityYear", years)
                    .list();
            return entities;
        } catch (Exception e) {
            throw new RuntimeException("Can't get a coach by experience greater than: " + years, e);
        }
    }
}
