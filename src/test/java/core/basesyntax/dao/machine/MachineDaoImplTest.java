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
    public void addMachine_Ok() {
        Machine machine = new Machine();
        machine.setMaker("China");
        machine.setYear(2015);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void addCar_Ok() {
        Car car = new Car();
        car.setHorsePower(100);
        car.setModel("Tavriya");
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void addTruck_Ok() {
        Truck truck = new Truck();
        truck.setMaxAllowedWeight(100);
        truck.setColor("Yellow");
        Machine actual = machineDao.save(truck);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByAgeOlderThan_Ok() {
        Machine machine = new Machine();
        machine.setYear(1990);
        machine.setMaker("USA");
        Car car = new Car();
        car.setModel("Zaporozhets");
        car.setYear(1950);
        car.setHorsePower(100);
        car.setMaker("Ukraine");
        Truck truck = new Truck();
        truck.setColor("Red");
        truck.setMaxAllowedWeight(100);
        truck.setMaker("France");
        truck.setYear(2012);
        List<Machine> actual = machineDao.findByAgeOlderThan(5);
        Assert.assertNotNull(actual);
        Assert.assertEquals(3, actual.size());
    }
}
