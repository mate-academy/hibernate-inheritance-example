package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> query = session.createQuery("from coach c "
                    + "where c.experience > : experience", Coach.class);
            query.setParameter("experience", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find list of coaches by years: " + years);
        }
    }
}
