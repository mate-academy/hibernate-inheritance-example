package core.basesyntax.dao.figure;

import core.basesyntax.model.figure.Figure;
import java.util.List;
import java.util.Optional;

public interface FigureDao<T extends Figure> {
    Optional<T> getId(Long id, Class<T> clazz);

    T save(T figure);

    List<T> findByColor(String color, Class<T> clazz);
}
