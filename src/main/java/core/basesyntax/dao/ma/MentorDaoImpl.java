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
        String hql = "from Mentor mentor where mentor.age > :age";
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hql)
                    .setParameter("age", age)
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find Mentor", e);
        }
    }
}
