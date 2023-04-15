package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Coach> cq = cb.createQuery(Coach.class);
            Root<Coach> root = cq.from(Coach.class);
            cq.where(cb.gt(root.get("experience"), years));

            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get coaches with experience greater than: "
                + years, e);
        }
    }
}
