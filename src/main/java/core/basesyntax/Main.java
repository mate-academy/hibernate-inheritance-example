package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.*;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.library.Injector;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
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


public class Main {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoImpl(HibernateUtil.getSessionFactory());
        Person person = new Person();
        person.setName("person");
        person.setAge(20);
        personDao.save(person);
        Mentor mentor = new Mentor();
        mentor.setAge(20);
        mentor.setName("bob");
        personDao.save(mentor);
        MentorDao mentorDao = new MentorDaoImpl(HibernateUtil.getSessionFactory());
        System.out.println(mentorDao.findByAgeGreaterThan(18));
        Coach coach = new Coach();
        coach.setExperience(5);
        coach.setTrack(Coach.Track.JAVA);
        coach.setAge(33);
        coach.setName("alice");
        personDao.save(coach);
        CoachDao coachDao = new CoachDaoImpl(HibernateUtil.getSessionFactory());
        System.out.println(coachDao.findByExperienceGreaterThan(4));

//        MachineDao machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());
//        Machine machine = new Machine();
//        machine.setMaker("machine");
//        machine.setYear(1999);
//        machineDao.save(machine);
//        System.out.println(machineDao.findByAgeOlderThan(2000));
//        Car car = new Car();
//        car.setHorsePower(112);
//        car.setMaker("honda");
//        car.setModel("civic");
//        car.setYear(2000);
//        machineDao.save(car);
//        Truck truck = new Truck();
//        truck.setColor("blue");
//        truck.setMaxAllowedWeight(2000);
//        truck.setMaker("supertruck");
//        truck.setYear(2001);
//        machineDao.save(truck);


//        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
//        Animal animal = new Animal();
//        animal.setAge(13);
//        animal.setName("Monkey");
//        animalDao.save(animal);
//        Cat cat = new Cat();
//        cat.setColor("black");
//        cat.setNumberOfLives(2);
//        cat.setAge(22);
//        cat.setName("pushock");
//        animalDao.save(cat);
//        Dog dog = new Dog();
//        dog.setOwner("Bob");
//        dog.setTailLength(12);
//        dog.setAge(12);
//        dog.setName("Box");
//        animalDao.save(dog);
//        System.out.println(animalDao.findByNameFirstLetter('b'));
//
//        FigureDao<Triangle> triangleFigureDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
//        Triangle triangle = new Triangle();
//        triangle.setArea(12);
//        triangle.setColor("black");
//        triangleFigureDao.save(triangle);
//        System.out.println(triangleFigureDao.findByColor("black", Triangle.class));
//        FigureDao<Circle> circleFigureDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
//        Circle circle = new Circle();
//        circle.setRadius(123);
//        circle.setColor("white");
//        circleFigureDao.save(circle);
    }
}
