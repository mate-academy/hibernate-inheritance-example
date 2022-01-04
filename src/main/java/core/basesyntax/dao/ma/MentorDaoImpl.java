package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.HibernateException;
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
            Query<Mentor> getMentorsByAgeGreaterThan =
                    session.createQuery("from Mentor where age > :years", Mentor.class);
            getMentorsByAgeGreaterThan.setParameter("years", age);
            return getMentorsByAgeGreaterThan.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't get list of mentors where age greater than "
                    + age, e);
        }
    }
}
