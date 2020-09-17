package core.basesyntax.model.machine.dao;

import java.util.List;
import core.basesyntax.model.machine.Machine;

public interface MachineDao {
    Machine save(Machine machine);

    List<Machine> getOlderThen(Integer years);
}
