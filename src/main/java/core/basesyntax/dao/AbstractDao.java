package core.basesyntax.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao {
    protected final SessionFactory sessionFactory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T saveEntity(T entity) {
        Transaction transaction = null;
        Session session = sessionFactory.openSession();
        try {
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save "
                    + entity.getClass().getSimpleName() + " = [" + entity + "]", e);
        } finally {
            session.close();
        }
        return entity;
    }
}
