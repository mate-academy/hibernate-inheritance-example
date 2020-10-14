package core.basesyntax.dao.machine;

import core.basesyntax.dao.GeneralDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;

public interface MachineDao extends GeneralDao<Machine> {
    List<Machine> findByAgeOlderThan(int age);
}
