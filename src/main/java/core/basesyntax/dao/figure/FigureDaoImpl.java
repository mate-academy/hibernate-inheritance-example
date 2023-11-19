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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(String.format("Can't add figure: %s to DB", figure), e);
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
            String dynamicQuery = String.format("FROM %s f WHERE f.color = %s", clazz.getName(), color);
            Query<T> getFigureByColorQuery = session.createQuery(dynamicQuery, clazz);
            return getFigureByColorQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get figures by color: " + color, e);
        }
    }
}
