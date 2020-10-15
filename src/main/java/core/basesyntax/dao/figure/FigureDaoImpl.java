package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao<T> implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Figure WHERE сolor LIKE " + color, clazz)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find figure" + clazz.getSimpleName()
                    + " by its first letter " + color, e);
        }
    }
}
