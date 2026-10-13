package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
        Session session = null;
        try {
            session = sessionFactory.openSession();
            return session.createQuery(
                            "from Mentor m "
                                    + "WHERE m.age > :age ",
                            Mentor.class)
                    .setParameter("age", age)
                    .list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any "
                    + Mentor.class.getSimpleName() + " with age of "
                    + age, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
