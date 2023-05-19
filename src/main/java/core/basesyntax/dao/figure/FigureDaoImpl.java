package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
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
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("can`t save " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> figuresQuery;
            if (clazz.equals(Circle.class)) {
                figuresQuery = (Query<T>) session.createQuery("FROM circle_figure cf "
                        + "WHERE cf.color = :color", Circle.class);
            } else {
                figuresQuery = (Query<T>) session.createQuery("FROM triangle_figure tf "
                        + "WHERE tf.color = :color", Triangle.class);
            }
            return figuresQuery.setParameter("color", color).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("can't find any figure by color " + color, e);
        }
    }
}
