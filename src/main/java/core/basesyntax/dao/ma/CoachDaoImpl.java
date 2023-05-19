package core.basesyntax.dao.ma;

import core.basesyntax.exceptin.DataProcessingException;
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
            return session.createQuery("FROM coach_person c WHERE c.experience > :years")
                    .setParameter("years", years)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find coach with experience more then "
                    + years, e);
        }
    }
}
