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
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = (Transaction) session.beginTransaction();
        try {
            session.persist(figure);
            session.flush();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't save figure", e);
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<T> result = session.createQuery(
                            "FROM " + clazz.getSimpleName() + " WHERE color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't find by color", e);
        }
    }
}
