package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MachineDaoImplTest extends AbstractTest {
    private Car ford;
    private Truck volvo;
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
    public void setUp() {
        machineDao = new MachineDaoImpl(getSessionFactory());
        ford = new Car(1967, "USA", 500, "ford GT-40");
        volvo = new Truck(2000, "Sweden", "black", 60000);
    }

    @Test
    public void testSaveTruck() {
        machineDao.save(volvo);
        Assert.assertEquals(volvo.getId().longValue(), 1L);
    }

    @Test
    public void testSaveCar() {
        machineDao.save(ford);
        Assert.assertEquals(ford.getId().longValue(), 1L);
    }

    @Test
    public void testGetByOlderThanOk() {
        machineDao.save(ford);
        machineDao.save(volvo);
        Assert.assertEquals(machineDao.findByAgeOlderThan(2015).size(), 0);
    }
}
