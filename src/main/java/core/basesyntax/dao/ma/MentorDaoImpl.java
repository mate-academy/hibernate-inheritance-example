package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Mentor WHERE age > :age", Mentor.class)
                    .setParameter("age", String.valueOf(age))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor by age greater than: " + age, e);
        }
    }
}
