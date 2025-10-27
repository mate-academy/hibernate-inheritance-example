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

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(
                    "Can't save figure: " + figure + " , error: ", e);
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {

        try (Session session = sessionFactory.openSession()) {
            String entityName = clazz.getSimpleName();
            return session
                    .createQuery("FROM " + entityName + " e WHERE e.color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't find figure by color: " + color + " , error: ", e);
        }
    }
}
