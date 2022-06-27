package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.time.LocalDate;
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
            int experienceYear = LocalDate.now().getYear()
                    - LocalDate.now().minusYears(years).getYear();
            Query<Coach> coachQuery = session.createQuery(
                    "FROM Coach WHERE experience > :value", Coach.class);
            coachQuery.setParameter("value", experienceYear);
            return coachQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t find coach by years: " + years, e);
        }
    }
}
