package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    private static final String FIGURE_COLOR_PARAM = "color";
    private static final Map<Class<? extends Figure>, String> mapOfFigureChildrenClasses = Map
                    .of(Circle.class, "Circle",
                    Triangle.class, "Triangle");

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
            throw new DataProcessingException("Can't insert a figure: " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        String hql = "FROM " + mapOfFigureChildrenClasses.get(clazz) + " f"
                + " WHERE f.color = :" + FIGURE_COLOR_PARAM;
        try (Session session = sessionFactory.openSession()) {
            Query<T> getFiguresByColor = session.createQuery(hql, clazz);
            getFiguresByColor.setParameter("color", color);
            return getFiguresByColor.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any figure by color: " + color, e);
        }
    }
}
