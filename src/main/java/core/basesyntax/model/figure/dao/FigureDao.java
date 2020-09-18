package core.basesyntax.model.figure.dao;

import java.util.List;

public interface FigureDao<T> {
    T save(T figure);

    List<T> getByColor(String color);
}
