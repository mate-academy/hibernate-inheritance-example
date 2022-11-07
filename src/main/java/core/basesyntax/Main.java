package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.ma.PersonDao;
import core.basesyntax.dao.ma.PersonDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        Animal animal = new Animal(1, "Animal 1");
        Cat cat = new Cat(3, "murka", 9, "gray");
        Dog dog = new Dog(3, "Milka", 32, "Ihor");
        animalDao.save(animal);
        animalDao.save(cat);
        animalDao.save(dog);
        animalDao.findByNameFirstLetter('m').forEach(System.out::println);

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        Machine machine = new Machine(2022, "Mercedes");
        Car car = new Car(2020, "Nissan", 140, "Sentra");
        Truck truck = new Truck(2021, "DAF", "White", 18.000);
        machineDao.save(machine);
        machineDao.save(car);
        machineDao.save(truck);
        machineDao.findByAgeOlderThan(1).forEach(System.out::println);

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        Person person = new Person(41, "Andriy");
        Mentor mentor1 = new Mentor(42, "Ivan");
        Mentor mentor2 = new Mentor(38, "Petro");
        Coach coach1 = new Coach(43, "Ivan", 15, Coach.Track.FE);
        Coach coach2 = new Coach(43, "Oleh", 23, Coach.Track.QA);
        personDao.save(person);
        personDao.save(mentor1);
        personDao.save(mentor2);
        personDao.save(coach1);
        personDao.save(coach2);
        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.findByAgeGreaterThan(41).forEach(System.out::println);
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.findByExperienceGreaterThan(20).forEach(System.out::println);

        FigureDao figureDao = new FigureDaoImpl(sessionFactory);
        Circle circle1 = new Circle("White", 10);
        Circle circle2 = new Circle("Red", 20);
        Triangle triangle1 = new Triangle("White", 100.4);
        Triangle triangle2 = new Triangle("Green", 150.8);
        figureDao.save(circle1);
        figureDao.save(circle2);
        figureDao.save(triangle1);
        figureDao.save(triangle2);
        figureDao.findByColor("White", Circle.class).forEach(System.out::println);
        figureDao.findByColor("White", Triangle.class).forEach(System.out::println);

        System.out.println("end");
    }
}
