package core.basesyntax.model.zoo.dao;

import java.util.List;

public interface AnimalDao<T> {
    T save(T animal);

    List<T> findByFirstLetter(Character letter);
}
