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
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery(
                            "from Coach c "
                                    + "WHERE c.experience > :years ",
                            Coach.class)
                    .setParameter("years", years)
                    .list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any "
                    + Coach.class.getSimpleName() + " with "
                    + years + " of experience", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
