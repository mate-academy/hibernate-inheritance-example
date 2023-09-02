package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import java.util.NoSuchElementException;
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
            Query<Mentor> getMentorByExperienceGreaterThanQuery =
                    session.createQuery("FROM Mentor AS m "
                            + "WHERE m.age > :age", Mentor.class);
            getMentorByExperienceGreaterThanQuery.setParameter("age", age);
            return getMentorByExperienceGreaterThanQuery.getResultList();
        } catch (Exception e) {
            throw new NoSuchElementException("Can't get all mentor greater than " + age, e);
        }
    }
}
