package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Person;
import java.util.List;

public interface PersonDao {
    Person save(Person person);

    List<Person> findByAgeGreater(int age);
}
