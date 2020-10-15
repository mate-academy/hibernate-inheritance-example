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
        machine.setMaker("England");
        machine.setYear(2004);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCar_Ok() {
        Car car = new Car();
        car.setMaker("German");
        car.setYear(2008);
        car.setModel("BMW");
        car.setHorsePower(200);
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTruck_Ok() {
        Truck truck = new Truck();
        truck.setMaker("USA");
        truck.setYear(2005);
        truck.setColor("blue");
        truck.setMaxAllowedWeight(500);
        Machine actual = machineDao.save(truck);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByAgeOlderThan_Ok() {
        Machine machine = new Machine();
        machine.setMaker("China");
        machine.setYear(2015);
        Machine machineDB = machineDao.save(machine);

        Machine machine2 = new Machine();
        machine2.setMaker("China");
        machine2.setYear(2000);
        Machine machine2DB = machineDao.save(machine2);

        Car car = new Car();
        car.setMaker("Japanese");
        car.setYear(2013);
        car.setModel("Mazda");
        car.setHorsePower(250);
        Machine carDB = machineDao.save(car);

        Car car2 = new Car();
        car2.setMaker("China");
        car2.setYear(2009);
        car2.setModel("Hyundai");
        car2.setHorsePower(300);
        Machine car2DB = machineDao.save(car2);

        Truck truck = new Truck();
        truck.setMaker("France");
        truck.setYear(2017);
        truck.setColor("green");
        truck.setMaxAllowedWeight(700);
        Machine truckDB = machineDao.save(truck);

        Truck truck2 = new Truck();
        truck2.setMaker("Hungary");
        truck2.setYear(2007);
        truck2.setColor("grey");
        truck2.setMaxAllowedWeight(1000);
        Machine truck2DB = machineDao.save(truck2);

        List<Machine> actualMachine = machineDao.findByAgeOlderThan(2012);
        List<Machine> expectedMachine = List.of(machine2DB, truck2DB, car2DB);
        Assert.assertEquals(actualMachine, expectedMachine);
    }
}
