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
        return new Class[]{
                Car.class,
                Machine.class,
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
        machine.setMaker("Volvo");
        machine.setYear(2015);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCar_Ok() {
        Car car = new Car();
        car.setYear(2019);
        car.setHorsePower(480);
        car.setModel("2107");
        car.setMaker("Vaz");
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTruck_Ok() {
        Truck truck = new Truck();
        truck.setYear(2020);
        truck.setColor("yellow");
        truck.setMaxAllowedWeight(20);
        truck.setMaker("DAF");
        Machine actual = machineDao.save(truck);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findCircleByColor_Ok() {
        Machine machine = new Machine();
        machine.setMaker("country where it made");
        machine.setYear(2020);
        machineDao.save(machine);
        Car car = new Car();
        car.setModel("X5");
        car.setMaker("BMW");
        car.setYear(2015);
        car.setHorsePower(220);
        machineDao.save(car);
        Truck truck = new Truck();
        truck.setMaker("DAF");
        truck.setYear(2018);
        truck.setColor("yellow");
        truck.setMaxAllowedWeight(480.50);
        machineDao.save(truck);
        List<Machine> machineList = machineDao.findByAgeOlderThan(1);
        Assert.assertEquals(2, machineList.size());
    }
}
