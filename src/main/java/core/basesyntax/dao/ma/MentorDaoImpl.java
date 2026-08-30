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
            CriteriaQuery<Mentor> query =
                    criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> root = query.from(Mentor.class);
            Predicate agePredicate = criteriaBuilder.greaterThan(root.get("age"), age);
            query.select(root).where(agePredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get list of mentors greater than: " + age, e);
        }
    }
}
