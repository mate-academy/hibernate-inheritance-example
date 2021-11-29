package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query findByAgeQuery =
                    session.createQuery("FROM Mentor m "
                            + "WHERE m.age > :age");
            findByAgeQuery.setParameter("age", age);
            return findByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get mentors older than: " + age, e);
        }
    }
}
