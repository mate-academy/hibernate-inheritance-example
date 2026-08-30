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
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot save a figure: " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String clazzName = clazz.getSimpleName();
            Query<T> getFigureByColor = session.createQuery(
                    "SELECT t FROM " + clazzName + " t "
                            + "WHERE t.color = :requiredColor",
                    clazz);
            getFigureByColor.setParameter("requiredColor", color);
            return getFigureByColor.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Cannot find figure: " + clazz.getSimpleName() + " by color: " + color, e);
        }
    }
}
