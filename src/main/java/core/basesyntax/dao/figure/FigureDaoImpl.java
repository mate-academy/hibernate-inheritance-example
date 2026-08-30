package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
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
        Session session = null;
        Transaction tr = null;
        try {
            session = sessionFactory.openSession();
            tr = session.beginTransaction();
            session.persist(figure);
            tr.commit();
        } catch (Exception e) {
            if (tr != null) {
                tr.rollback();
            }
            throw new RuntimeException("Cannot save figure", e);
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
            return session.createQuery(
                    "from " + clazz.getSimpleName()
                            + " o where o.color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cant find figure with this color: " + color, e);
        }
    }
}
