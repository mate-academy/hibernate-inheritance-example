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
    private Car teslaS;
    private Truck cyberTruck;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Machine.class,
                Car.class,
                Truck.class
        };
    }

    @Before
    public void setUp() {
        machineDao = new MachineDaoImpl(getSessionFactory());
        teslaS = new Car();
        teslaS.setHorsePower(500);
        teslaS.setModel("Tesla Model S");
        teslaS.setMaker("Mask");
        teslaS.setYear(2020);

        cyberTruck = new Truck();
        cyberTruck.setColor("Metalic");
        cyberTruck.setMaxAllowedWeight(1000);
        cyberTruck.setMaker("Mask");
        cyberTruck.setYear(2020);
    }

    @Test
    public void saveAndGetMachine() {
        machineDao.save(teslaS);
        machineDao.save(cyberTruck);
        Assert.assertEquals(machineDao.findByAgeOlderThan(2022).size(), 2);
    }
}
