package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
    public void createMachie_Ok() {
        Machine machine = new Machine();
        machine.setMaker("BMW");
        machine.setYear(2005);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCar_Ok() {
        Car car = new Car();
        car.setHorsePower(300);
        car.setModel("Hatchback");
        Car actual = (Car) machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByYear_Ok() {
        Car car = new Car();
        car.setHorsePower(300);
        car.setYear(2000);
        Truck truck = new Truck();
        truck.setColor("red");
        truck.setYear(2003);
        machineDao.save(car);
        machineDao.save(truck);
        List<Machine> oldMachines = machineDao.findByAgeOlderThan(7);
        Assert.assertNotNull(oldMachines);
        Assert.assertEquals(2, oldMachines.size());
    }
}
