package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rollbackEx) {
                    throw new DataProcessingException("Rollback failed.", rollbackEx);
                }
            }
            throw new DataProcessingException("Can`t save Figure to DB.", e);
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        if (color == null) {
            throw new IllegalArgumentException("Color can`t be null");
        }
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT f FROM " + clazz.getSimpleName() + " f WHERE f.color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        }
    }
}
