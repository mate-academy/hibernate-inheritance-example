package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.ma.Person;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class PersonDaoImpl extends AbstractDao<Person> implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Person> findByAgeGreater(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Person> query = session.createQuery(
                    "from Person p where p.age > :age", Person.class);
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get mentors with age greater than " + age + " years");
        }
    }
}
