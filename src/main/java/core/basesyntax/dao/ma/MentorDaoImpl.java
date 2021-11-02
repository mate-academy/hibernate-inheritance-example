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
            Query<Mentor> query = session.createQuery("FROM Mentor m WHERE age > :age",
                    Mentor.class);
            query.setParameter("age", age).getResultList();
            List<Mentor> resultList = query.getResultList();
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException("Unable to get mentor by age" + age, e);
        }
    }
}
