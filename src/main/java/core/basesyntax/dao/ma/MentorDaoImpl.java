package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
            Query<Mentor> mentorsGreaterThanAgeQuery = session.createQuery("SELECT m FROM Mentor m "
                    + "WHERE age > :age", Mentor.class);
            mentorsGreaterThanAgeQuery.setParameter("age", age);
            return mentorsGreaterThanAgeQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find mentors older than age" + age, e);
        }
    }
}
