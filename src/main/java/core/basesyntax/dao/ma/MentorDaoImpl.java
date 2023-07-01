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
            Query<Mentor> personQuery = session.createQuery("FROM mentor m "
                    + "WHERE m.age > :age", Mentor.class);
            personQuery.setParameter("age", age);
            return personQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Wow, can't find Mentor with age greater than " + age, e);
        }
    }
}
