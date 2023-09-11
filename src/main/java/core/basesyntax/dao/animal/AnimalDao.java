package core.basesyntax.dao.animal;

import core.basesyntax.model.zoo.Animal;
import java.util.List;
import java.util.Optional;

public interface AnimalDao {
    Optional<Animal> getId(Long id);

    Animal save(Animal animal);

    List<Animal> findByNameFirstLetter(Character character);
}
