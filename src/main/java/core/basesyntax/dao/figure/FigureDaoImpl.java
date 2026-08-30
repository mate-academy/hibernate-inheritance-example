package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.DaoOperation;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    private final DaoOperation<T> figureDaoOperation =
            new DaoOperation<>();

    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        return figureDaoOperation.add(figure, sessionFactory);
    }

    @Override
    public List<T> findByColor(String color, Class<T> cls) {
        try (Session session = sessionFactory.openSession()) {
            Query<T> figureQuery =
                    session.createQuery("FROM " + cls.getName() + " f "
                    + "WHERE f.color = :color", cls);
            figureQuery.setParameter("color", color);
            return figureQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not get figure by color "
                    + color + " and class " + cls.getName(), e);
        }
    }
}
