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
            Query<Mentor> getListOfMentorsByAge = session.createQuery(
                    "SELECT m FROM Mentor m "
                            + "WHERE m.age > :required",
                    Mentor.class);
            getListOfMentorsByAge.setParameter("required", age);
            return getListOfMentorsByAge.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot find mentor by age: " + age);
        }
    }
}
