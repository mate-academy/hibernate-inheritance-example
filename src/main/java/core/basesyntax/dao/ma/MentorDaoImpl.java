package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try {
            return sessionFactory.fromSession(session -> session.createQuery("from Mentor m "
                            + "where m.age > :age", Mentor.class)
                    .setParameter("age", age)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor with age greater than: " + age, e);
        }
    }
}
