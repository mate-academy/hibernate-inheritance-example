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
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(figure);
            tx.commit();
            return figure;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw new RuntimeException("Error saving figure", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String findByColor = "from " + clazz.getSimpleName() + " where color = :color";
            Query<T> query = session.createQuery(findByColor, clazz);
            query.setParameter("color", color);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Error finding figures by color", e);
        }
    }
}
