package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao<Figure> implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        return (T) super.save(figure);
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from " + clazz.getSimpleName()
                    + " where color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get figures of color: " + color, e);
        }
    }
}
