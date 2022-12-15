package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
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
            Query query = session.createQuery("from Person r "
                    + "where r.age > :age", Person.class);
            query.setParameter("age", age);
            return query.getResultList();
        }
    }
}
