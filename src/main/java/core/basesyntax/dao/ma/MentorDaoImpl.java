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
            String hql = "FROM Mentor where age > :age";
            List<Mentor> mentorList = session.createQuery(hql, Mentor.class)
                    .setParameter("age", age)
                    .getResultList();
            return mentorList;
        } catch (Exception e) {
            throw new RuntimeException("Can't get a list of mentors: ", e);
        }
    }
}
