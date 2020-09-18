package core.basesyntax.model.machine.dao;

import core.basesyntax.model.machine.Machine;
import java.util.List;

public interface MachineDao {
    Machine save(Machine machine);

    List<Machine> getOlderThan(Integer years);
}
