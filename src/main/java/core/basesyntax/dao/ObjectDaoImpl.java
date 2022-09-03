package core.basesyntax.dao;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class ObjectDaoImpl<T extends Object> extends AbstractDao implements ObjectDao<T> {

    public ObjectDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T item) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(item);
            transaction.commit();
            return item;
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save to DB " + item, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
