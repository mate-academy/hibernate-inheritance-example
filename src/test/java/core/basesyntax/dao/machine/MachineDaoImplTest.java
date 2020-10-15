package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MachineDaoImplTest extends AbstractTest {
    private MachineDao machineDao;

    @Before
    public void setUp() throws Exception {
        machineDao = new MachineDaoImpl(getSessionFactory());
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Machine.class,
                Car.class,
                Truck.class
        };
    }

    @Test
    public void createMachine_Ok() {
        Machine machine = new Machine();
        machine.setYear(2000);
        machine.setMaker("Germany");
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCat_Ok() {
        Machine car = new Car();
        car.setYear(2005);
        car.setMaker("France");
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createDog_Ok() {
        Machine truck = new Truck();
        truck.setYear(2010);
        truck.setMaker("Belarus");
        Machine actual = machineDao.save(truck);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByAgeOlderThan20_Ok() {
        Machine car = new Car();
        car.setYear(1990);
        machineDao.save(car);
        Machine truck = new Truck();
        truck.setYear(2010);
        machineDao.save(truck);
        List<Machine> actual = machineDao.findByAgeOlderThan(20);
        List<Machine> expected = new ArrayList<>();
        expected.add(car);
        Assert.assertTrue(actual.size() == 1);
        Assert.assertTrue(actual.get(0).equals(car));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void findByAgeOlderThan20_Fail() {
        Machine car = new Car();
        car.setYear(2005);
        machineDao.save(car);
        Machine truck = new Truck();
        truck.setYear(2010);
        machineDao.save(truck);
        List<Machine> actual = machineDao.findByAgeOlderThan(20);
        List<Machine> expected = new ArrayList<>();
        expected.add(car);
        Assert.assertTrue(actual.get(0) != null);
    }
}
