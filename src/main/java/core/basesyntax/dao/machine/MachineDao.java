package core.basesyntax.dao.machine;

import core.basesyntax.model.machine.Machine;
import java.util.List;

public interface MachineDao {
    Object save(Machine machine);

    List<Machine> findByAgeOlderThan(int age);
}
