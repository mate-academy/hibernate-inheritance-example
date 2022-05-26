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
            Query<Mentor> findGreaterAgeQuery = session.createQuery("FROM Mentor m "
                    + "WHERE m.age > :age", Mentor.class);
            findGreaterAgeQuery.setParameter("age", age);
            return findGreaterAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find by age " + age, e);
        }
    }
}
