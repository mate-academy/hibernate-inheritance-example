package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Circle;
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
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save figure: " + figure + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> withNeededColor = session.createQuery(
                    "FROM " + (clazz.equals(Circle.class) ? "Circle " : "Triangle ")
                            + "WHERE color =:color", clazz);
            withNeededColor.setParameter("color", color);
            return withNeededColor.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find " + clazz.getName() + " by color: " + color, e);
        }
    }
}
