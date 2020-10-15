package core.basesyntax.dao.figure;

import core.basesyntax.dao.GeneralDaoImpl;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends GeneralDaoImpl<T> implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM " + clazz.getSimpleName()
                            + " c WHERE c.color = :color", clazz)
                    .setParameter("color", color)
                    .getResultList();
        }
    }
}
