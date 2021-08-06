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
            Query<Mentor> findByAgeGreaterThan = session.createQuery("from Person p "
                    + "where p.age > :age", Mentor.class);
            findByAgeGreaterThan.setParameter("age", age);
            return findByAgeGreaterThan.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get Mentor with age greater than " + age, e);
        }
    }
}
