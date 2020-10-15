package core.basesyntax.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory sessionFactory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected T save(T element) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(element);
            transaction.commit();
            return element;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add entity: " + element, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
