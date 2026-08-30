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
            String hql = "FROM Mentor WHERE age > :wantedAge";
            return session.createQuery(hql, Mentor.class)
                    .setParameter("wantedAge", age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not find mentors with age greater than: "
                    + age, e);
        }
    }
}
