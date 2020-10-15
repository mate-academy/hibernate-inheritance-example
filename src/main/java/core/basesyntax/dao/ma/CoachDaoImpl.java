package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> criteriaQuery = criteriaBuilder.createQuery(Coach.class);
            Root<Coach> root = criteriaQuery.from(Coach.class);
            criteriaQuery.select(root).where(criteriaBuilder.gt(root.get("experience"), years));
            return session.createQuery(criteriaQuery).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't findAll", e);
        }
    }
}
