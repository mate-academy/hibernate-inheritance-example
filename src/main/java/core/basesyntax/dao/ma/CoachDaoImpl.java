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
            return session.createQuery("SELECT c FROM Coach c"
                            + " WHERE c.experience > :experience", Coach.class)
                    .setParameter("experience", years)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find Coach Person with experience more years than: "
                    + years, e);
        }
    }
}
