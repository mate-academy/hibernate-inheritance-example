package core.basesyntax.model;

import java.util.List;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.machine.dao.MachineDao;
import core.basesyntax.model.machine.dao.impl.MachineDaoImpl;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.model.zoo.dao.CatDao;
import core.basesyntax.model.zoo.dao.DogDao;
import core.basesyntax.model.zoo.dao.impl.CatDaoImpl;
import core.basesyntax.model.zoo.dao.impl.DogDaoImpl;

public class Main {
    public static void main(String[] args) {
        CatDao catDao = new CatDaoImpl();
        for (Cat cat : getCats()) {
            catDao.save(cat);
        }
        DogDao dogDao = new DogDaoImpl();
        for (Dog dog : getDogs()) {
            dogDao.save(dog);
        }
        List<Cat> cats = catDao.findByFirstLetter('A');
        List<Dog> dogs = dogDao.findByFirstLetter('A');


        MachineDao machineDao = new MachineDaoImpl();
        for (Machine machine: getMachines()) {
            machineDao.save(machine);
        }
        machineDao.getOlderThen(4).forEach(System.out::println);



    }

    private static List<Machine> getMachines() {
        return List.of(new Car(2015),
                new Car(2020),
                new Car(2010),
                new Car(2012),
                new Truck(2015),
                new Truck(2016),
                new Truck(2010),
                new Truck(2012));
    }

    private static List<Cat> getCats() {
        return List.of(new Cat("Adam"),
                new Cat("Brad"),
                new Cat("Avn"),
                new Cat("Merlin"),
                new Cat("Vovan"));
    }


    private static List<Dog> getDogs() {
        return List.of(new Dog("April"),
                new Dog("Brad"),
                new Dog("Adamant"),
                new Dog("Merlin"),
                new Dog("Vovan"));
    }

}
