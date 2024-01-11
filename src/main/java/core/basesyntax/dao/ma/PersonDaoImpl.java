package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.ma.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersonDaoImpl extends AbstractDao implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person person) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(person);
            transaction.commit();
            return person;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save person to DB" + person, e);
        } finally {
            session.close();
        }
    }
}
