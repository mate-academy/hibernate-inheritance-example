package core.basesyntax.dao.animal;

import core.basesyntax.dao.GeneralDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;

public interface AnimalDao extends GeneralDao<Animal> {
    List<Animal> findByNameFirstLetter(Character character);
}
