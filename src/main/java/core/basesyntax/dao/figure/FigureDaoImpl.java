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
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add an figure - " + figure
                    + " to DB", e);
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
            return session.createQuery("FROM " + clazz.getName() + " f"
                    + " WHERE f.color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find figures with color: " + color, e);
        }
    }
}
