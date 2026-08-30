package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Mentor where age > :age", Mentor.class)
                    .setParameter("age", age)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find mentor by age", e);
        }
    }
}
