package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Mentor m "
                            + "WHERE m.age > :years", Mentor.class)
                    .setParameter("years", age)
                    .getResultList();
        } catch (Exception e) {
            throw new HibernateException("Can't get by age: " + age, e);
        }
    }
}
