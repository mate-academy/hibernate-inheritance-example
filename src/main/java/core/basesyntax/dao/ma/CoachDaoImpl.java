package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
            String hql =
                    "FROM Coach c WHERE c.experience >:years";
            Query<Coach> query = session.createQuery(hql, Coach.class);
            query.setParameter("years", years);
            return query.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find coach by experience greater than: "
                    + years, e);
        }
    }
}
