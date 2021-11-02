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
        String findByAgeGreaterThanQuery = "FROM Mentor m WHERE m.age > :age";
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> mentorQuery =
                    session.createQuery(findByAgeGreaterThanQuery, Mentor.class);
            mentorQuery.setParameter("age", age);
            return mentorQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get mentor by age grater then " + age, e);
        }
    }
}
