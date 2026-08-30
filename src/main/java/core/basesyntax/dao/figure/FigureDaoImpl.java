package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save figure: " + figure, e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        List<T> query;
        Session session = null;

        try {
            session = sessionFactory.openSession();
            query = session.createQuery("FROM " + clazz.getName() + " f"
                            + " WHERE f.color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Failed to find figure by color", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return query;
    }
}
