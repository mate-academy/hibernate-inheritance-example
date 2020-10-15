package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcesingException;
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
            Query<Coach> query = session.createQuery("FROM Coach "
                    + "WHERE experience > :param",Coach.class);
            query.setParameter("param", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcesingException("Can't get Mentors with experience > " + years, e);
        }
    }
}
