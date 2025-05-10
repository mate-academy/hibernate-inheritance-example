package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
        String hql = "FROM Mentor m WHERE m.age > :age";
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> getMentorsByAge = session.createQuery(hql, Mentor.class);
            getMentorsByAge.setParameter("age", age);
            return getMentorsByAge.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any mentors with "
                                                + "age greater than: " + age, e);
        }
    }
}
