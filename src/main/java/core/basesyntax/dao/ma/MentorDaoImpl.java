package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.Query;
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
            Query query = session.createQuery("FROM Mentor WHERE age > :ageThreshold",
                    Mentor.class);
            query.setParameter("ageThreshold", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error while retrieving mentors with age greater than: "
                    + age, e);
        }
    }
}
