package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> query = builder.createQuery(Mentor.class);
            Root<Mentor> root = query.from(Mentor.class);
            Predicate condition = builder.greaterThan(root.get("age"), age);
            query.select(root).where(condition);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not get all Mentors with age"
                    + " greater than: " + age, e);
        }
    }
}
