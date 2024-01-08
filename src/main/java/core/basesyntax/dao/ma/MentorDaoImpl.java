package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> criteriaQuery = criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> root = criteriaQuery.from(Mentor.class);
            Predicate expGreat = criteriaBuilder.gt(root.get("age"), age);
            criteriaQuery.where(expGreat);
            Query<Mentor> query = session.createQuery(criteriaQuery);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a Mentor by age: " + age, e);
        }
    }
}
