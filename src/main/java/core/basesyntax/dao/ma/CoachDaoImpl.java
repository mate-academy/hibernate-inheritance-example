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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Coach c where c.experience > :years ");
        query.setParameter("years", years);
        List<Coach> resultList = query.getResultList();
        return resultList;
    }
}
