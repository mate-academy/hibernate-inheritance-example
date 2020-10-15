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
    public void add_machine_car_test_ok() {
        Car car = new Car();
        car.setYear(1988);
        car.setModel("Q5");
        machineDao.save(car);
        Assert.assertNotNull(car);
        Assert.assertNotNull(car.getId());
        Assert.assertEquals(1L, car.getId().longValue());
    }

    @Test
    public void add_machine_truck_test_ok() {
        Truck truck = new Truck();
        truck.setYear(1988);
        truck.setColor("black");
        machineDao.save(truck);
        Assert.assertNotNull(truck);
        Assert.assertNotNull(truck.getId());
        Assert.assertEquals(1L, truck.getId().longValue());
    }

    @Test
    public void get_list_of_machines_by_year() {
        Truck truck = new Truck();
        truck.setYear(1998);
        truck.setColor("black");
        Car car = new Car();
        car.setYear(2010);
        car.setModel("Q5");
        machineDao.save(truck);
        machineDao.save(car);
        Assert.assertNotNull(machineDao.findByAgeOlderThan(11));
        Assert.assertEquals(1, machineDao.findByAgeOlderThan(11).size());
    }
}
