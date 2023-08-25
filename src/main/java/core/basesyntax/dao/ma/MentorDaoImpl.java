package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    private static final String AGE = "age";

    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> mentorQuery = session.createQuery("FROM Mentor "
                         + "WHERE age > :age", Mentor.class);
            mentorQuery.setParameter(AGE, age);
            return mentorQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't fina a mentor by age: " + age, e);
        }
    }
}
