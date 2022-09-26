package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
            return session.createQuery("FROM Coach c "
                            + "WHERE c.experience > :year", Coach.class)
                    .setParameter("year", years)
                    .list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get a coach by years: " + years, e);
        }
    }
}
