package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import jakarta.persistence.Query;
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
            Query query = session.createQuery("FROM Coach WHERE experience > :yearsThreshold");
            query.setParameter("yearsThreshold", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving"
                    + " coaches with experience greater than: " + years, e);
        }
    }
}
