package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import java.util.Collections;
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
            throw new RuntimeException("Can't add a figure in DB:" + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        if (clazz.equals(Triangle.class)) {
            try (Session session = sessionFactory.openSession()) {
                Query triangleQuery
                        = session.createQuery("from Triangle t where t.color = :color");
                triangleQuery.setParameter("color",color);
                return triangleQuery.getResultList();
            }
        }
        if (clazz.equals(Circle.class)) {
            try (Session session = sessionFactory.openSession()) {
                Query circleQuery
                        = session.createQuery("from Circle c where c.color = :color");
                circleQuery.setParameter("color",color);
                return circleQuery.getResultList();
            }
        }
        return Collections.emptyList();
    }
}
