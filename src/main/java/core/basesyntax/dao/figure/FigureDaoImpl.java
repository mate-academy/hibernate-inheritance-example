package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import jakarta.persistence.Entity;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        if (figure == null) {
            throw new IllegalArgumentException("Figure cannot be null");
        }
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            session.persist(figure);
            session.flush();
            tx.commit();
            return figure;
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class type cannot be null");
        }
        if (color == null) {
            throw new IllegalArgumentException("Color cannot be null");
        }

        String entityName = clazz.isAnnotationPresent(Entity.class)
                && !clazz.getAnnotation(Entity.class).name().isEmpty()
                ? clazz.getAnnotation(Entity.class).name()
                : clazz.getSimpleName();

        try (var session = getSession()) {
            var tx = session.beginTransaction();
            var query = session.createQuery(
                    "FROM " + entityName + " f WHERE f.color = :color",
                    clazz
            );
            query.setParameter("color", color);
            List<T> result = query.getResultList();
            tx.commit();
            return result;
        }
    }
}
