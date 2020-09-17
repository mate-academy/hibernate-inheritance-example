package core.basesyntax.model;

import java.util.List;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.dao.CoachDao;
import core.basesyntax.model.ma.dao.MentorDao;
import core.basesyntax.model.ma.dao.impl.CoachDaoImpl;
import core.basesyntax.model.ma.dao.impl.MentorDaoImpl;
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
        catDao.findByFirstLetter('A').forEach(System.out::println);
        dogDao.findByFirstLetter('A').forEach(System.out::println);

        MachineDao machineDao = new MachineDaoImpl();
        for (Machine machine : getMachines()) {
            machineDao.save(machine);
        }
        machineDao.getOlderThan(4).forEach(System.out::println);

        CoachDao coachDao = new CoachDaoImpl();
        for (Coach coach : getCoaches()) {
            coachDao.save(coach);
        }
        MentorDao mentorDao = new MentorDaoImpl();
        for (Mentor mentor : getMentors()) {
            mentorDao.save(mentor);
        }
        coachDao.getWithExperienceMoreThan(5).forEach(System.out::println);
        mentorDao.getOlderThan(20).forEach(System.out::println);
    }


    private static List<Coach> getCoaches() {
        return List.of(new Coach(6),
                new Coach(3),
                new Coach(4),
                new Coach(10),
                new Coach(8));
    }

    private static List<Mentor> getMentors() {
        return List.of(new Mentor(18),
                new Mentor(20),
                new Mentor(21),
                new Mentor(19),
                new Mentor(23));
    }

    private static List<Machine> getMachines() {
        return List.of(new Car(2015),
                new Car(2020),
                new Car(2010),
                new Car(2012),
                new Car(2009),
                new Truck(2015),
                new Truck(2016),
                new Truck(2021),
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
