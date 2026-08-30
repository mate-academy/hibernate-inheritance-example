package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
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
            String findMentorsQuery = "from Mentor mentor where mentor.age > :age";
            Query<Mentor> query = session.createQuery(findMentorsQuery, Mentor.class);
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding mentors by age: " + age, e);
        }
    }
}
