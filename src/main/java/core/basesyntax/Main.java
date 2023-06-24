package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());

        Animal parrot = new Animal();
        parrot.setName("Givi");
        parrot.setAge(2);
        animalDao.save(parrot);

        Cat murzik = new Cat(9, "black");
        animalDao.save(murzik);
        Dog bobik = new Dog(20, "Ihor");
        animalDao.save(bobik);

        MachineDao machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());

        Machine machine = new Machine();
        machine.setYear(1999);
        machine.setMaker("Volvo");
        machineDao.save(machine);

        Car car = new Car(221, "XC50");
        machineDao.save(car);
        Truck truck = new Truck("yellow", 2.11);
        machineDao.save(truck);
    }
}
