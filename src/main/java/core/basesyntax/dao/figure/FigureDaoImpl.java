package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.util.HibernateUtil;
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
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert figure " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<T> query = session.createQuery(
                    "FROM " + clazz.getName() + " f WHERE f.color =:color", clazz);
            query.setParameter("color", color);
            System.out.println(query.list().size());
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find figure by color: " + color, e);
        }
    }
}
