package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    private final SessionFactory sessionFactory;

    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            String hql = "FROM Mentor m WHERE m.age > :age";
            return session.createQuery(hql, Mentor.class)
                    .setParameter("age", age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentors with age greater than "
                    + age + " years", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
