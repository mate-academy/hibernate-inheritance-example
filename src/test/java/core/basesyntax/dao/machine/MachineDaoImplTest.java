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
        return new Class[]{
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
        Machine machine = new Machine();
        machine.setMaker("Mercedes-Benz");
        machine.setYear(2020);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCar_Ok() {
        Car car = new Car();
        car.setYear(2010);
        car.setHorsePower(500);
        car.setModel("Polo Hatchback");
        car.setMaker("VW");
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTruck_Ok() {
        Truck truck = new Truck();
        truck.setYear(1998);
        truck.setColor("blue");
        truck.setMaxAllowedWeight(1200);
        truck.setMaker("Volvo Trucks");
        Machine actual = machineDao.save(truck);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
