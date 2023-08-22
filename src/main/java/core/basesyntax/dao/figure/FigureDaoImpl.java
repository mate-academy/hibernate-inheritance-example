package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    private static final String COLOR_PARAMETER = "color";

    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
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
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM "
                    + (clazz.equals(Triangle.class) ? "Triangle " : "Circle ")
                    + "WHERE color = :" + COLOR_PARAMETER;
            Query<T> getMachineByAgeQuery =
                    session.createQuery(hql, clazz);
            getMachineByAgeQuery.setParameter(COLOR_PARAMETER, color);
            return getMachineByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a figure by color: " + color, e);
        }
    }
}
