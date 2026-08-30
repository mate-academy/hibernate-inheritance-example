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
import core.basesyntax.model.ma.Coach.Track;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        FigureDao<Circle> circleDao = new FigureDaoImpl<>(sessionFactory);
        Circle circle = new Circle();
        circle.setColor("Green");
        circle.setRadius(10);
        circleDao.save(circle);
        System.out.println("Saved circle: " + circleDao.findByColor("Green", Circle.class));

        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(sessionFactory);
        Triangle triangle = new Triangle();
        triangle.setColor("Pink");
        triangle.setArea(30.5);
        triangleDao.save(triangle);
        System.out.println("Saved triangle: " + triangleDao.findByColor("Pink", Triangle.class));
        //
        Coach coach = new Coach();
        coach.setName("John");
        coach.setAge(35);
        coach.setExperience(15);
        coach.setTrack(Track.JAVA);
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.save(coach);
        System.out.println("Saved coach: " + coachDao.findByExperienceGreaterThan(5));

        Mentor mentor = new Mentor();
        mentor.setName("Ivan");
        mentor.setAge(30);
        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.save(mentor);

        //
        Car car = new Car();
        car.setMaker("Ford");
        car.setYear(2011);
        car.setModel("S-Max");
        car.setHorsePower(160);
        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(car);

        Truck truck = new Truck();
        truck.setMaker("Lexus");
        truck.setYear(2020);
        truck.setColor("Red");
        truck.setMaxAllowedWeight(2000.5);
        machineDao.save(truck);
        System.out.println("Machines older than 2021: " + machineDao.findByAgeOlderThan(5));

        //
        Cat cat = new Cat();
        cat.setName("Barsik");
        cat.setAge(5);
        cat.setColor("Black");
        cat.setNumberOfLives(9);
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(cat);

        Dog dog = new Dog();
        dog.setName("Batler");
        dog.setAge(6);
        dog.setOwner("Antony");
        dog.setTailLength(10);
        animalDao.save(dog);

        Dog dog2 = new Dog();
        dog2.setName("Bobik");
        dog2.setAge(8);
        dog2.setOwner("John");
        dog2.setTailLength(8);
        animalDao.save(dog2);

        List<Animal> animalList = animalDao.findByNameFirstLetter('B');
        System.out.println("\nAnimals start with name 'B: ");
        for (Animal animal: animalList) {
            System.out.println(" - " + animal);
        }
    }
}
