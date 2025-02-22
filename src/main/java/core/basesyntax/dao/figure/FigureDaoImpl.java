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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error saving figure", e);
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery(
                    "FROM " + clazz.getName()
                            + " WHERE color = :color", clazz);
            query.setParameter("color", color);
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error finding figure by color : " + color, e);
        }
    }
}
