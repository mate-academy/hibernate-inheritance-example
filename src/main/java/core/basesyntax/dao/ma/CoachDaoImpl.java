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
        String hql = "from Coach c where c.experience > :years";
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hql)
                    .setParameter("years", years)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find Coach", e);
        }
    }
}
