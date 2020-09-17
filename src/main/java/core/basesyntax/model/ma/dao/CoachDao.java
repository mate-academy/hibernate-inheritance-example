package core.basesyntax.model.ma.dao;

import java.util.List;
import core.basesyntax.model.ma.Coach;

public interface CoachDao extends PersonDao<Coach> {
    List<Coach> getWithExperienceMoreThan(Integer years);
}
