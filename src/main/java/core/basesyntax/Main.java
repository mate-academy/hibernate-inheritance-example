package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        testSingleTable(sessionFactory);
        testTablePerClass(sessionFactory);
        testJoinedTable(sessionFactory);
        testMappedSuperclass(sessionFactory);

        sessionFactory.close();
    }

    private static void testSingleTable(SessionFactory sessionFactory) {
        System.out.println("=== Testing Single Table (Animals) ===");

        Cat cat = new Cat();
        cat.setName("Fluffy");
        cat.setAge(3);
        cat.setNumberOfLives(9);
        cat.setColor("Orange");

        Dog dog = new Dog();
        dog.setName("Buddy");
        dog.setAge(5);
        dog.setTailLength(20);
        dog.setOwner("John");

        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(cat);
        animalDao.save(dog);

        System.out.println("Animals starting with 'F': "
                + animalDao.findByNameFirstLetter('F'));
    }

    private static void testTablePerClass(SessionFactory sessionFactory) {
        System.out.println("\n=== Testing Table Per Class (Machines) ===");

        Car car = new Car();
        car.setYear(2010);
        car.setMaker("Toyota");
        car.setHorsePower(200);
        car.setModel("Camry");

        Truck truck = new Truck();
        truck.setYear(2015);
        truck.setMaker("Volvo");
        truck.setColor("Blue");
        truck.setMaxAllowedWeight(18000);

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(car);
        machineDao.save(truck);

        System.out.println("Machines older than 10 years: "
                + machineDao.findByAgeOlderThan(10));
    }

    private static void testJoinedTable(SessionFactory sessionFactory) {
        System.out.println("\n=== Testing Joined Table (Persons) ===");

        Coach coach = new Coach();
        coach.setName("Alex");
        coach.setAge(35);
        coach.setExperience(8);
        coach.setTrack(Coach.Track.JAVA);

        Mentor mentor = new Mentor();
        mentor.setName("Bob");
        mentor.setAge(28);

        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.save(coach);

        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.save(mentor);

        System.out.println("Coaches with experience > 5: "
                + coachDao.findByExperienceGreaterThan(5));
        System.out.println("Mentors with age > 25: "
                + mentorDao.findByAgeGreaterThan(25));
    }

    private static void testMappedSuperclass(SessionFactory sessionFactory) {
        System.out.println("\n=== Testing MappedSuperclass (Figures) ===");

        Circle circle = new Circle();
        circle.setColor("Red");
        circle.setRadius(10);

        Triangle triangle = new Triangle();
        triangle.setColor("Red");
        triangle.setArea(25.5);

        FigureDao<Circle> circleDao = new FigureDaoImpl<>(sessionFactory);
        circleDao.save(circle);

        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(sessionFactory);
        triangleDao.save(triangle);

        System.out.println("Red circles: "
                + circleDao.findByColor("Red", Circle.class));
        System.out.println("Red triangles: "
                + triangleDao.findByColor("Red", Triangle.class));
    }
}
