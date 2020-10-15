package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import java.util.List;
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
    public void createMachine_Ok() {
        Car car = new Car();
        car.setYear(2018);
        car.setMaker("Germany");
        car.setModel("BMW");
        car.setHorsePower(250);
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByAgeOlderThan_Ok() {
        Car car = new Car();
        car.setYear(2018);
        Truck truck = new Truck();
        truck.setYear(2002);
        machineDao.save(car);
        machineDao.save(truck);
        List<Machine> machines = machineDao.findByAgeOlderThan(10);
        Assert.assertEquals(1, machines.size());
    }
}
