package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MachineDaoImplTest extends AbstractTest {
    private MachineDao machineDao;
    
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Machine.class,
                Car.class,
                Truck.class
        };
    }
    
    @Before
    public void setUp() throws Exception {
        machineDao = new MachineDaoImpl(getSessionFactory());
    }
    
    @Test
    public void save() {
        Machine machine = new Car();
        machine.setMaker("Me");
        machine.setYear(100);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
    }
    
    @Test
    public void selectByAge() {
        Machine machine1 = new Car();
        machine1.setMaker("Me");
        machine1.setYear(100);
        Machine machine2 = new Car();
        machine2.setMaker("Me");
        machine2.setYear(1);
        machineDao.save(machine1);
        machineDao.save(machine2);
        Assert.assertNotNull(machineDao.findByAgeOlderThan(10).get(0));
    }
}
