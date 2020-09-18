package core.basesyntax.model.ma.dao;

import core.basesyntax.model.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public interface PersonDao<T> {
    default T save(T person) {
        Transaction transaction = null;
        Session session = null;
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
            throw new RuntimeException("Cannot insert "
                    + person.getClass().getSimpleName() + " entity - " + person, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
