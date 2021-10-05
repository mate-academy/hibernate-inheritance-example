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
            Query<Mentor> findByAgeQuery =
                    session.createQuery("FROM Mentor "
                            + "WHERE age > :age", Mentor.class);
            findByAgeQuery.setParameter("year", age);
            return findByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get mentors older than: " + age, e);
        }
    }
}
