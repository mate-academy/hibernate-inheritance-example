package core.basesyntax.model.ma.dao;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> criteriaQuery
                    = criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> root = criteriaQuery.from(Mentor.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.greaterThan(root.get("age"), age)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving mentors", e);
        }
    }
}
