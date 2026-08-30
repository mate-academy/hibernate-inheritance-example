package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        try {
            sessionFactory.inTransaction(s -> s.persist(figure));
            return figure;
        } catch (Exception e) {
            throw new RuntimeException("Can't add figure to the DB", e);
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try {
            return sessionFactory.fromSession(s -> s.createQuery("FROM " + clazz.getSimpleName()
                    + " WHERE color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("No " + clazz.getSimpleName().toLowerCase()
            + " with such color", e);
        }
    }
}
