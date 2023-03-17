package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
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
            Query<Person> query = session.createQuery(
                    "FROM Person p WHERE p.age > :age", Person.class);
            query.setParameter("age", age);
            List<Mentor> mentorList = new ArrayList<>();
            for (Person person : query.getResultList()) {
                if (person instanceof Mentor) {
                    mentorList.add((Mentor) person);
                }
            }
            return mentorList;
        } catch (Exception e) {
            throw new RuntimeException("Can not get MentorList by findByAgeGreaterThan. Age: "
                    + age, e);
        }
    }
}
