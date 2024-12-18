package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> mentorCriteriaQuery = cb.createQuery(Mentor.class);
            Root<Mentor> root = mentorCriteriaQuery.from(Mentor.class);
            mentorCriteriaQuery.where(cb.greaterThan(root.get("age"), age));
            Query<Mentor> sessionQuery = session.createQuery(mentorCriteriaQuery);
            return sessionQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor by age " + age, e);
        }
    }
}
