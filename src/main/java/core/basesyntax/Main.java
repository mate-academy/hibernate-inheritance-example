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
        Animal animal = new Animal();
        animal.setAge(5);
        animal.setName("Abstract animal");
        Cat cat = new Cat();
        cat.setColor("Gray");
        cat.setNumberOfLives(9);
        cat.setAge(3);
        cat.setName("Musia");
        Dog dog = new Dog();
        dog.setOwner("Bob");
        dog.setTailLength(15);
        dog.setName("Charlie");
        dog.setAge(5);

        Machine machine = new Machine();
        machine.setMaker("Abstract machine");
        machine.setYear(1900);
        Car car = new Car();
        car.setHorsePower(720);
        car.setModel("GTR");
        car.setMaker("VW");
        car.setYear(2015);
        Truck truck = new Truck();
        truck.setColor("White");
        truck.setMaxAllowedWeight(15000);
        truck.setMaker("Volvo");
        truck.setYear(2020);

        Person person = new Person();
        person.setAge(0);
        person.setName("Abstract person");
        Coach coach = new Coach();
        coach.setExperience(2);
        coach.setTrack(Coach.Track.JAVA);
        coach.setAge(25);
        coach.setName("Alice");
        Mentor mentor = new Mentor();
        mentor.setAge(20);
        mentor.setName("Andrew");

        Circle circle = new Circle();
        circle.setRadius(10);
        circle.setColor("Blue");
        Triangle triangle = new Triangle();
        triangle.setArea(30);
        triangle.setColor("Red");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(cat);
        animalDao.save(dog);
        animalDao.findByNameFirstLetter('M').forEach(System.out::println);
        animalDao.findByNameFirstLetter('C').forEach(System.out::println);

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        personDao.save(coach);
        personDao.save(mentor);
        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.findByAgeGreaterThan(15).forEach(System.out::println);
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.findByExperienceGreaterThan(1).forEach(System.out::println);

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(car);
        machineDao.save(truck);
        machineDao.save(machine);
        machineDao.findByAgeOlderThan(1990);

        FigureDao figureDao = new FigureDaoImpl<>(sessionFactory);
        figureDao.save(circle);
        figureDao.save(triangle);
        figureDao.findByColor("Red", Triangle.class).forEach(System.out::println);

    }
}
