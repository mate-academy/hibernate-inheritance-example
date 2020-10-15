package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> query = builder.createQuery(Coach.class);
            Root<Coach> root = query.from(Coach.class);
            Predicate greatThanYears = builder.greaterThan(root.get("experience"), years);
            query.select(root).where(greatThanYears);
            return session.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw new RuntimeException("Can't get list of coaches with experience greater than "
                    + years, exception);
        }
    }
}
