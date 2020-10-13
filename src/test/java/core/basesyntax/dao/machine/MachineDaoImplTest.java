package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void createCar_Ok() {
        Car car = new Car();
        car.setYear(2018);
        car.setMaker("BMW");
        car.setModel("530 D");
        car.setHorsePower(286);

        Car actual = (Car) dao.save(car);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByYear() {
        Car car = new Car();
        car.setYear(2019);
        car.setMaker("BMW");
        car.setModel("530 D");
        car.setHorsePower(286);
        dao.save(car);

        car = new Car();
        car.setYear(2010);
        car.setMaker("BMW");
        car.setModel("530 D");
        car.setHorsePower(206);
        dao.save(car);

        Truck truck = new Truck();
        truck.setYear(2015);
        truck.setMaker("MAN");
        truck.setMaxAllowedWeight(25);
        truck.setColor("white");
        dao.save(truck);
        List<Machine> machines = dao.findByAgeOlderThan(2);
        Assert.assertEquals(2, machines.size());
        Car actual = (Car) machines.get(0);
        Assert.assertEquals("530 D", actual.getModel());
    }
}
