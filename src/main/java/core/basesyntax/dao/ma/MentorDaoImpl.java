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
            Query<Mentor> getAllFilteredMentors =
                    session.createQuery("from Mentor m where m.age > :a", Mentor.class);
            getAllFilteredMentors.setParameter("a", age);
            return getAllFilteredMentors.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't retrieve mentors from db.", e);
        }
    }
}
