package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.setAge(2);
        cat.setName("cat");
        ((Cat) cat).setColor("black");
        ((Cat) cat).setNumberOfLives(7);
        Animal dog = new Dog();
        dog.setName("dog");
        dog.setAge(3);
        ((Dog) dog).setOwner("Max");
        ((Dog) dog).setTailLength(30);
        AnimalDaoImpl animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        animalDao.save(cat);
        animalDao.save(dog);
        List<Animal> animals = animalDao.findByNameFirstLetter('D');
        System.out.println(animals);
        System.out.println("---------------------------------------------");

        Machine car = new Car();
        car.setYear(2000);
        car.setMaker("Canada");
        ((Car)car).setModel("Hamster gt80");
        ((Car)car).setHorsePower(200);
        Machine truck = new Truck();
        truck.setYear(2015);
        truck.setMaker("France");
        ((Truck)truck).setColor("Red");
        ((Truck)truck).setMaxAllowedWeight(500);
        MachineDaoImpl machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());
        machineDao.save(car);
        machineDao.save(truck);
        List<Machine> machines = machineDao.findByAgeOlderThan(4);
        System.out.println(machines);
    }
}
