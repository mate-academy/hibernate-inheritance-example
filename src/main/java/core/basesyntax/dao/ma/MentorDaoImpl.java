package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> criteriaQuery =
                    criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> root = criteriaQuery.from(Mentor.class);
            Predicate experiencePredicate = criteriaBuilder.greaterThan(root.get("age"),
                    age);
            criteriaQuery.select(root).where(experiencePredicate);
            List<Mentor> result = session.createQuery(criteriaQuery).getResultList();
            return result;
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor age greater than "
                    + age, e);
        }
    }
}
