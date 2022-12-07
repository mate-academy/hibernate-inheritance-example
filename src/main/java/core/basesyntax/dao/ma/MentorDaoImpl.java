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
            Query query = session.createQuery("SELECT m FROM Mentor m "
                    + "WHERE m.age > :age");
            query.setParameter("age",age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all mentors with age greater than: " + age, e);
        }
    }
}
