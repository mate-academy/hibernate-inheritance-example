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
        Animal animal = new Animal();
        animal.setAge(2);
        animal.setName("animal");
        animalDao.save(animal);

        Cat cat = new Cat();
        cat.setColor("black");
        cat.setName("Cat");
        cat.setAge(3);
        animalDao.save(cat);

        Dog dog = new Dog();
        dog.setOwner("owner");
        dog.setAge(1);
        dog.setName("dog");
        animalDao.save(dog);

        System.out.println(animalDao.findByNameFirstLetter('d'));

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        Machine machine = new Machine();
        machine.setYear(2023);
        machine.setMaker("maker");
        machineDao.save(machine);

        Car car = new Car();
        car.setYear(2024);
        car.setMaker("maker");
        car.setHorsePower(250);
        car.setModel("model");
        machineDao.save(car);

        Truck truck = new Truck();
        truck.setYear(2022);
        truck.setMaker("maker");
        truck.setColor("black");
        truck.setMaxAllowedWeight(5000);
        machineDao.save(truck);

        System.out.println(machineDao.findByAgeOlderThan(2));

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        Person person = new Person();
        person.setName("Person");
        person.setAge(20);
        personDao.save(person);

        final CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        Coach coach = new Coach();
        coach.setName("Coach");
        coach.setAge(21);
        coach.setTrack(Coach.Track.JAVA);
        coach.setExperience(2);
        coachDao.save(coach);

        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        Mentor mentor = new Mentor();
        mentor.setName("Mentor");
        mentor.setAge(23);
        mentorDao.save(mentor);

        System.out.println(coachDao.findByExperienceGreaterThan(1));
        System.out.println(mentorDao.findByAgeGreaterThan(20));

        FigureDao<Circle> circleDao = new FigureDaoImpl<>(sessionFactory);
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(10);
        circleDao.save(circle);

        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(sessionFactory);
        Triangle triangle = new Triangle();
        triangle.setColor("blue");
        triangle.setArea(20);
        triangleDao.save(triangle);

        System.out.println(circleDao.findByColor("red", Circle.class));
        System.out.println(triangleDao.findByColor("blue", Triangle.class));
    }
}
