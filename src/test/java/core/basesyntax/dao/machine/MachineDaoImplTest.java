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
    private MachineDao dao;

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
        dao = new MachineDaoImpl(getSessionFactory());
    }

    @Test
    public void machineDaoTests() {
        Car car = new Car();
        car.setYear(2019);
        car.setMaker("BMW");
        car.setModel("530 D");
        car.setHorsePower(286);
        dao.save(car);
        Assert.assertNotNull(car);
        Assert.assertNotNull(car.getId());
        Assert.assertEquals(1L, car.getId().longValue());
        car = new Car();
        car.setYear(2010);
        car.setMaker("BMW");
        car.setModel("530 D");
        car.setHorsePower(206);
        dao.save(car);
        Assert.assertNotNull(car);
        Assert.assertNotNull(car.getId());
        Assert.assertEquals(2L, car.getId().longValue());
        Truck truck = new Truck();
        truck.setYear(2015);
        truck.setMaker("MAN");
        truck.setMaxAllowedWeight(25);
        truck.setColor("white");
        dao.save(truck);
        Assert.assertNotNull(truck);
        Assert.assertNotNull(truck.getId());
        Assert.assertEquals(3L, truck.getId().longValue());

        List<Machine> machines = dao.findByAgeOlderThan(2);
        Assert.assertEquals(2, machines.size());
        Car actual = (Car) machines.get(0);
        Assert.assertEquals("530 D", actual.getModel());
    }
}
