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
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            var query = session.createQuery(
                    "FROM Mentor m WHERE m.age > :age",
                    Mentor.class
            );
            query.setParameter("age", age);
            List<Mentor> result = query.getResultList();
            tx.commit();
            return result;
        }
    }
}
