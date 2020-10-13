package core.basesyntax.dao;

import core.basesyntax.HibernateUtil;
import core.basesyntax.model.zoo.Animal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class AbstractDao<T> {
    protected final SessionFactory sessionFactory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public T save(T entity) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert " + entity.getClass().getSimpleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
