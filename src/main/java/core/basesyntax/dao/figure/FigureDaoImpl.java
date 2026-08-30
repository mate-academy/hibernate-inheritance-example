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
            throw new RuntimeException("Can't insert a figure: " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        List<T> figuresList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {
            if (clazz == Circle.class) {
                figuresList = session.createQuery("FROM Circle "
                                + "WHERE color = :color", clazz)
                        .setParameter("color", color)
                        .getResultList();
            } else if (clazz == Triangle.class) {
                figuresList = session.createQuery("FROM Triangle "
                                + "WHERE color = :color", clazz)
                        .setParameter("color", color)
                        .getResultList();
            }
        } catch (Exception e) {
            throw new RuntimeException("Can't find a figure by color: " + color, e);
        }
        return figuresList;
    }
}

