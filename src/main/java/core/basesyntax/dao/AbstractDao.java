package core.basesyntax.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    protected final SessionFactory sessionFactory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session openSession() {
        return this.sessionFactory.openSession();
    }

    protected void closeSession(Session session) {
        if (session != null) {
            session.close();
        }
    }

    protected void transactionNullCheck(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }
}
