package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.sound.midi.Track;

public class MachineDaoImplTest extends AbstractTest {
    private MachineDao machineDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Machine.class,
                Truck.class,
                Car.class
        };
    }

    @Before
    public void setUp() throws Exception {
        machineDao = new MachineDaoImpl(getSessionFactory());
    }

    @Test
    public void createMachine_Ok() {
        Machine machine = new Machine();
        machine.setMaker("Toyota");
        machine.setYear(2014);
        Machine actual = machineDao.save(machine);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTruck_Ok() {
        Truck track = new Truck();
        track.setColor("blue");
        track.setMaxAllowedWeight(1400);
        Machine actual = machineDao.save(track);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCar_Ok() {
        Car car = new Car();
        car.setHorsePower(200);
        car.setModel("BMW");
        car.setYear(2020);
        Machine actual = machineDao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
