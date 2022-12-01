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
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            assert transaction != null;
            transaction.rollback();
            throw new RuntimeException("Cant add figure to DB" + e);
        } finally {
            assert session != null;
            session.close();
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> query = session.createQuery("FROM " + clazz.getName() + " AS t "
                    + "WHERE t.color = :color", clazz);
            query.setParameter("color", color);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find figure " + clazz
                    + " with color: " + color, e);
        }
    }
}
