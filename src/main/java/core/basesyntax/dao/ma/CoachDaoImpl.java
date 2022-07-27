package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.Collections;
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
            Query<Coach> query = session.createQuery(
                    "from Coach c WHERE c.experience > :yearExp", Coach.class);
            query.setParameter("yearExp", years);
            List<Coach> coaches = query.getResultList();
            return coaches.size() == 0 ? Collections.emptyList() : coaches;
        } catch (Exception e) {
            throw new RuntimeException("Cannot get list of coaches with EXP greater than "
                    + years + " years");
        }
    }
}
