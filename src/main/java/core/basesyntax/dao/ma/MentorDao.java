package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;

public interface MentorDao extends PersonDao {
    Mentor save(Mentor mentor);

    List<Mentor> findByAgeGreaterThan(int age);
}
