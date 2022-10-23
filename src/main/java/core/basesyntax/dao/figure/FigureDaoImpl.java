package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import java.util.ArrayList;
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
            throw new RuntimeException("Can`t insert to DB figure:" + figure);
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
            if (clazz == Circle.class) {
                Query<Circle> getCircleQuery = session.createQuery("from Circle c "
                        + "where c.color = :color", Circle.class);
                getCircleQuery.setParameter("color", color);
                return (List<T>) getCircleQuery.getResultList();
            }
            if (clazz == Triangle.class) {
                Query<Triangle> getTriangleQuery = session.createQuery("from Triangle t "
                        + "where t.color = :color", Triangle.class);
                getTriangleQuery.setParameter("color", color);
                return (List<T>) getTriangleQuery.getResultList();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can`t find in DB figure with color:" + color);
        }
        return new ArrayList<>();
    }
}
