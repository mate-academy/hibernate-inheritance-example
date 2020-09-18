package core.basesyntax.model.zoo.dao;

import java.util.List;
import core.basesyntax.model.zoo.Animal;

public interface AnimalDao {
    Animal save(Animal animal);

    List<Animal> findByFirstLetter(Character letter);
}
