package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import java.util.Objects;
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
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
        } catch (Exception e) {
            Objects.requireNonNull(transaction).rollback();
            throw new RuntimeException("Can't add figure to db", e);
        } finally {
            Objects.requireNonNull(session).close();
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM " + clazz.getName()
                            + " c WHERE c.color = :color", clazz)
                    .setParameter("color", color).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a figure with color: " + color, e);
        }
    }
}
