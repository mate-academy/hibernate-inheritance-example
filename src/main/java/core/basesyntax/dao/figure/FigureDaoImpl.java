package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
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
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cant save figure " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        Transaction transaction = null;
        List<T> results;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            String hql = "FROM " + clazz.getSimpleName() + " c WHERE c.color = :color";
            Query<T> query = session.createQuery(hql, clazz);
            query.setParameter("color", color);
            results = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't find entities of type "
                    + clazz.getSimpleName() + " by color " + color, e);
        }
        return results;
    }
}
