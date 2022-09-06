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
            Query<Mentor> findMentorByAgeQuery = session.createQuery("FROM Mentor m "
                    + "WHERE m.age > :age", Mentor.class);
            findMentorByAgeQuery.setParameter("age", age);
            return findMentorByAgeQuery.getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("I can't find a mentor older than age: " + age, e);
        }
    }
}
