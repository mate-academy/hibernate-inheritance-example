
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        MachineDao machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());

        Machine machine = new Machine();
        machine.setMaker("ZAZ");
        machine.setYear(2005);
        machineDao.save(machine);

        Car car = new Car();
        car.setMaker("bmw");
        car.setModel("316 Compact");
        car.setHorsePower(16);
        car.setYear(1657);
        machineDao.save(car);

        Truck truck = new Truck();
        truck.setColor("White");
        truck.setMaker("Volvo");
        truck.setYear(2010);
        truck.setMaxAllowedWeight(20.0);
        machineDao.save(truck);

        List<Machine> byAgeOlderThan = machineDao.findByAgeOlderThan(15);
        for (Machine machine1: byAgeOlderThan) {
            System.out.println(machine1);
        }


    }
}
