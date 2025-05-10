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
            throw new RuntimeException("Can't insert to DB a figure: " + figure);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        String clazzSimpleName = clazz.getSimpleName();
        try (Session session = sessionFactory.openSession()) {
            Query<T> clazzQuery = session
                    .createQuery("FROM " + clazzSimpleName + " cl WHERE cl.color =: color", clazz);
            clazzQuery.setParameter("color", color);
            return clazzQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find in DB "
                  + clazzSimpleName + " by color: " + color);
        }
    }
}
