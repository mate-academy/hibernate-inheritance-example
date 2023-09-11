package core.basesyntax.dao.machine;

import core.basesyntax.model.machine.Machine;
import java.util.List;
import java.util.Optional;

public interface MachineDao {
    Optional<Machine> getId(Long id);

    Machine save(Machine machine);

    List<Machine> findByAgeOlderThan(int age);
}
