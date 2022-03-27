package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (HibernateException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add figure: " + figure, ex);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> sessionQuery = session.createQuery("from " + clazz.getName()
                    + " where color = :color", clazz);
            sessionQuery.setParameter("color", color);
            return sessionQuery.getResultList();
        } catch (HibernateException ex) {
            throw new RuntimeException("Can't find figure by color: " + color, ex);
        }
    }
}
