package core.basesyntax.dao.ma;

import core.basesyntax.exeptions.DataProcessingException;
import core.basesyntax.model.ma.Coach;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> query = builder.createQuery(Coach.class);
            Root<Coach> root = query.from(Coach.class);
            query.where(builder.gt(root.get("experience"), years));
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get a list of coaches with "
                    + "experience greater than " + years, e);
        }
    }
}
