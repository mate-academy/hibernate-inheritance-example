package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Circle;
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
            throw new RuntimeException("Can't insert figure: " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        String query = "";
        if (clazz.equals(Circle.class)) {
            query = "from Circle figure where figure.color = :color";
        } else {
            query = "from Triangle figure where figure.color = :color";
        }
        try (Session session = sessionFactory.openSession()) {
            Query<T> getFigureByColor
                    = session.createQuery(query, clazz);
            getFigureByColor.setParameter("color", color);
            return getFigureByColor.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get figures by color:" + color, e);
        }
    }
}
