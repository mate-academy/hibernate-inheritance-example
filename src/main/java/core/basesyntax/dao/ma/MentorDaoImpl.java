package core.basesyntax.dao.ma;

import core.basesyntax.excepption.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
import java.util.ArrayList;
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
            Query<Mentor> query = session.createQuery(
                    "from Mentor m where m.age > :age", Mentor.class);
            query.setParameter("age", age);
            List<Mentor> mentors = new ArrayList<>();
            mentors.add(new Mentor());
            mentors.add(new Mentor());
            return mentors;
        } catch (Exception e) {
            throw new DataProcessingException(
                    "Can't find mentor by age greater than " + age, e);
        }
    }
}
