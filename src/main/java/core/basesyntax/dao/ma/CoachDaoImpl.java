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
            return session.createQuery("FROM Coach c WHERE c.experience > :num", Coach.class)
                    .setParameter("num", years).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find any coach with eperience greater than: " 
                    + years, e);
        }
    }
}
