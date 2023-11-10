package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
import java.util.List;
import core.basesyntax.model.ma.Person;
import jakarta.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Person p "
                    + "WHERE p.age > :age", Person.class);
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get age for mentor " + age, e);
        }
    }
}
