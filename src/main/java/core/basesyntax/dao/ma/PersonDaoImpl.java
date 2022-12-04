package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.ma.Person;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersonDaoImpl extends AbstractDao implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person person) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
            return person;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save person to DB " + person, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
