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
            List<Mentor> mentors = new ArrayList<>();
            for (Person person : query.getResultList()) {
                if (person instanceof Mentor) {
                    mentors.add((Mentor) person);
                }
            }
            return mentors;
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor with age greater than: "
                    + age, e);
        }
    }
}
