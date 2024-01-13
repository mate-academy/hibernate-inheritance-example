package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
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
            throw new DataProcessingException("Cannot add to db figure: " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            if (clazz == Triangle.class) {
                Query<Triangle> triangleQuery = session.createQuery("FROM Triangle t "
                        + "WHERE t.color = :c", Triangle.class);
                triangleQuery.setParameter("c", color);
                return (List<T>) triangleQuery.getResultList();
            } else {
                Query<Circle> circleQuery = session.createQuery("FROM Circle t "
                        + "WHERE t.color = :c", Circle.class);
                circleQuery.setParameter("c", color);
                return (List<T>) circleQuery.getResultList();
            }

        }
    }
}
