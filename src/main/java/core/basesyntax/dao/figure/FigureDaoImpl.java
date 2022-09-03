package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.ObjectDao;
import core.basesyntax.dao.ObjectDaoImpl;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {

    private final ObjectDao<T> objectDao = new ObjectDaoImpl<>(sessionFactory);

    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        return objectDao.save(figure);
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        return null;
    }
}
