package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure> extends AbstractDao<T> implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        String clazzSimpleName = clazz.getSimpleName();
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "from " + clazzSimpleName + " f where f.color = :color");
            query.setParameter("color", color);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can`t get figures of shape " + clazzSimpleName + " with color " + color);
        }
    }
}
