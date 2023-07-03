package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.ma.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class PersonDaoImpl extends AbstractDao implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person person) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(person);
            session.getTransaction().commit();
            return person;
        } catch (Exception e) {
            throw new RuntimeException("Can't save person " + person, e);
        }
    }
}
