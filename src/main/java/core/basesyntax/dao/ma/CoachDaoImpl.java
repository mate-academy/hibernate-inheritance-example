package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Coach WHERE experience > :years";
        return session.createQuery(hql, Coach.class)
                .setParameter("years", years)
                .getResultList();
    }
}
