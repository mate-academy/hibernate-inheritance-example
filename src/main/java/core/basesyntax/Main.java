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
import java.util.List;

public class Main {
    private static AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
    private static MachineDao machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());
    private static FigureDao<Circle> circleFigureDao =
            new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
    private static FigureDao<Triangle> triangleFigureDao =
            new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
    private static PersonDao personDao = new PersonDaoImpl(HibernateUtil.getSessionFactory());
    private static MentorDao mentorDao = new MentorDaoImpl(HibernateUtil.getSessionFactory());
    private static CoachDao coachDao = new CoachDaoImpl(HibernateUtil.getSessionFactory());

    public static void main(String[] args) {
        System.out.println("CREATING ANIMALS\n");

        Animal animal = new Animal();
        animal.setAge(2);
        animal.setName("Rex");
        animalDao.save(animal);

        Animal animal2 = new Animal();
        animal2.setAge(2);
        animal2.setName("Murzik");
        animalDao.save(animal2);

        Dog dog = new Dog();
        dog.setOwner("Vasya");
        dog.setTailLength(20);
        dog.setAge(3);
        dog.setName("Ross");
        animalDao.save(dog);

        Dog dog2 = new Dog();
        dog2.setOwner("Vasya");
        dog2.setTailLength(30);
        dog2.setAge(1);
        dog2.setName("Bars");
        animalDao.save(dog2);

        Cat cat = new Cat();
        cat.setColor("black");
        cat.setNumberOfLives(5);
        cat.setAge(6);
        cat.setName("Murka");
        animalDao.save(cat);

        Cat cat2 = new Cat();
        cat2.setColor("white");
        cat2.setNumberOfLives(5);
        cat2.setAge(6);
        cat2.setName("Rita");
        animalDao.save(cat2);

        List<Animal> actualAnimal = animalDao.findByNameFirstLetter('R');
        System.out.println("Animals from DB with name R");
        actualAnimal.forEach(System.out::println);

        List<Animal> actualDog = animalDao.findByNameFirstLetter('B');
        System.out.println("Animals from DB with name B");
        actualDog.forEach(System.out::println);

        List<Animal> actualCat = animalDao.findByNameFirstLetter('M');
        System.out.println("Animals from DB with name M");
        actualCat.forEach(System.out::println);

        System.out.println("\n\nCREATING MACHINES\n");

        Machine machine = new Machine();
        machine.setMaker("China");
        machine.setYear(2015);
        machineDao.save(machine);

        Machine machine2 = new Machine();
        machine2.setMaker("China");
        machine2.setYear(2000);
        machineDao.save(machine2);

        Car car = new Car();
        car.setMaker("Japanese");
        car.setYear(2013);
        car.setModel("Mazda");
        car.setHorsePower(250);
        machineDao.save(car);

        Car car2 = new Car();
        car2.setMaker("China");
        car2.setYear(2009);
        car2.setModel("Hyundai");
        car2.setHorsePower(300);
        machineDao.save(car2);

        Truck truck = new Truck();
        truck.setMaker("France");
        truck.setYear(2017);
        truck.setColor("green");
        truck.setMaxAllowedWeight(700);
        machineDao.save(truck);

        Truck truck2 = new Truck();
        truck2.setMaker("Hungary");
        truck2.setYear(2007);
        truck2.setColor("grey");
        truck2.setMaxAllowedWeight(1000);
        machineDao.save(truck2);

        System.out.println("MACHINES WITH AGE OLDER THEN 2012");
        List<Machine> actualMachine = machineDao.findByAgeOlderThan(2012);
        actualMachine.forEach(System.out::println);

        System.out.println("\n\nCREATING FIGURES\n");

        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(5);
        circleFigureDao.save(circle);

        Circle circle2 = new Circle();
        circle2.setColor("grey");
        circle2.setRadius(60);
        circleFigureDao.save(circle2);

        Triangle triangle = new Triangle();
        triangle.setColor("white");
        triangle.setArea(30);
        triangleFigureDao.save(triangle);

        Triangle triangle2 = new Triangle();
        triangle2.setColor("grey");
        triangle2.setArea(15);
        triangleFigureDao.save(triangle2);

        Triangle triangle3 = new Triangle();
        triangle3.setColor("blue");
        triangle3.setArea(30);
        triangleFigureDao.save(triangle3);

        List<Circle> actualCircleBlue = circleFigureDao.findByColor("blue", Circle.class);
        actualCircleBlue.forEach(System.out::println);

        List<Triangle> actualTriangleWhite = triangleFigureDao.findByColor("white", Triangle.class);
        actualTriangleWhite.forEach(System.out::println);

        System.out.println("\n\nCREATING MA\n");

        Person person = new Person();
        person.setName("Bob");
        person.setAge(20);
        personDao.save(person);

        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("John");
        coach.setExperience(3);
        coach.setTrack(Coach.Track.QA);
        personDao.save(coach);

        Mentor mentor = new Mentor();
        mentor.setAge(20);
        mentor.setName("Alice");
        personDao.save(mentor);

        List<Mentor> mentors = mentorDao.findByAgeGreaterThan(19);
        mentors.forEach(System.out::println);

        List<Coach> coaches = coachDao.findByExperienceGreaterThan(2);
        coaches.forEach(System.out::println);
    }
}
