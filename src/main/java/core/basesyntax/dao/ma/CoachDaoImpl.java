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
            String hql = "from Coach where experience > :years";
            return session.createQuery(hql, Coach.class)
                    .setParameter("years",years)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get list of machines: ", e);
        }
    }
}
