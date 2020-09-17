package core.basesyntax.model.ma.dao;

import java.util.List;
import core.basesyntax.model.ma.Mentor;

public interface MentorDao extends PersonDao<Mentor> {
    List<Mentor> getOlderThan(int years);
}
