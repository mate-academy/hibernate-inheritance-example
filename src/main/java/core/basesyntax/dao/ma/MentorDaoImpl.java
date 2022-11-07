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
            Query query = session.createQuery("FROM Mentor AS m "
                    + "WHERE m.age > :age");
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get mentor list by age greater than " + age, e);
        }
    }
}
