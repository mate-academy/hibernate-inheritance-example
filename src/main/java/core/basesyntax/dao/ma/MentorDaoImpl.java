package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    private static final String AGE_PARAMETER = "age";

    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Mentor m "
                                    + "WHERE m.age > :" + AGE_PARAMETER, Mentor.class)
                    .setParameter(AGE_PARAMETER, age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all mentors with age greater than "
                    + age, e);
        }
    }
}
