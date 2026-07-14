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
            Query<Mentor> coachQuery =
                    session.createQuery("from Mentor where age > :age",Mentor.class);
            coachQuery.setParameter("age", age);
            return coachQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find any Mentor from DB", e);
        }
    }
}
