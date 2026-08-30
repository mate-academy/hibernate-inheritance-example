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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can`t add figure " + figure + " to the DB", e);
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        String entityName = clazz.getSimpleName();
        String query = "From " + entityName + " f where f.color =:color";
        try (Session session = sessionFactory.openSession()) {
            Query<T> sessionQuery = session.createQuery(query, clazz);
            sessionQuery.setParameter("color", color);
            return sessionQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t find figures by color " + color, e);
        }
    }
}
