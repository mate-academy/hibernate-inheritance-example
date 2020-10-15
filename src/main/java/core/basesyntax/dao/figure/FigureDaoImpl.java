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
        try {
            session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try {
            Query<T> query = sessionFactory.openSession().createQuery(
                    "FROM " + clazz.getSimpleName() + " m WHERE m.color = :color ", clazz);
            query.setParameter("color", color);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("findByColor failed\n", e);
        }

    }
}
