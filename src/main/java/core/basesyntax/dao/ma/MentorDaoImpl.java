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
        List<Mentor> result = null;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            String query = "FROM Mentor m WHERE m.age > :age";
            result = session.createQuery(query, Mentor.class)
                    .setParameter("age", age)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find coach with greater experience ", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return result;
    }
}
