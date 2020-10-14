package core.basesyntax.dao.figure;

import core.basesyntax.dao.GeneralDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;

public interface FigureDao<T extends Figure> extends GeneralDao<T> {
    List<T> findByColor(String color, Class<T> clazz);
}
