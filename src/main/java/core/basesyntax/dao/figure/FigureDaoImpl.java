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
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot save figure " + figure + " in DB");
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String table = clazz.getSimpleName();
            Query<T> query = session.createQuery("from " + table
                    + " c WHERE c.color LIKE :fetched_color", clazz);
            query.setParameter("fetched_color", color);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get list of entities= "
                    + clazz + " from DB by color=" + color, e);
        }
    }
}
