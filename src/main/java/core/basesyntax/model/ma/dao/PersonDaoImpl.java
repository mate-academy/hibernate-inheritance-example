package core.basesyntax.model.ma.dao;

import core.basesyntax.model.ma.Person;
import core.basesyntax.model.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PersonDaoImpl implements PersonDao {
    @Override
    public Person save(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
            return person;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("There was an error inserting person "
                    + person, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
