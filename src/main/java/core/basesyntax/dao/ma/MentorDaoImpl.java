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
            String hql = "FROM Mentor WHERE age > :entityAge";
            List<Mentor> entities = session.createQuery(hql, Mentor.class)
                    .setParameter("entityAge", age)
                    .list();
            return entities;
        } catch (Exception e) {
            throw new RuntimeException("Can't get a mentor by experience greater than: " + age, e);
        }
    }
}
