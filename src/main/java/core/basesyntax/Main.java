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
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();
    private static final AnimalDao animalDao = new AnimalDaoImpl(factory);
    private static final MachineDao machineDao = new MachineDaoImpl(factory);
    private static final PersonDao personDao = new PersonDaoImpl(factory);
    private static final MentorDao mentorDao = new MentorDaoImpl(factory);
    private static final CoachDao coachDao = new CoachDaoImpl(factory);
    private static final FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(factory);
    private static final FigureDao<Circle> circleDao = new FigureDaoImpl<>(factory);

    public static void main(String[] args) {
        Cat bob = new Cat();
        bob.setColor("black");
        bob.setNumberOfLives(1);
        bob.setName("Bob");
        bob.setAge(1);

        Dog alice = new Dog();
        alice.setName("Alice");
        alice.setAge(5);
        alice.setOwner("Trump");
        alice.setTailLength(10);

        animalDao.save(bob);
        animalDao.save(alice);
        animalDao.findByNameFirstLetter('a').forEach(System.out::println);

        //machine
        Car bmw = new Car();
        bmw.setMaker("bmw");
        bmw.setYear(1999);
        bmw.setModel("X5");
        bmw.setHorsePower(1200);

        Truck truck = new Truck();
        truck.setMaker("lada corp");
        truck.setColor("white");
        truck.setMaxAllowedWeight(2000);
        truck.setYear(1975);

        machineDao.save(bmw);
        machineDao.save(truck);
        machineDao.findByAgeOlderThan(2001).forEach(System.out::println);

        //ma
        Mentor mentor = new Mentor();
        mentor.setName("Alice");
        mentor.setAge(20);

        Coach coach = new Coach();
        coach.setName("Bob");
        coach.setAge(25);
        coach.setExperience(5);
        coach.setTrack(Coach.Track.FE);

        personDao.save(coach);
        personDao.save(mentor);
        mentorDao.findByAgeGreaterThan(18).forEach(System.out::println);
        coachDao.findByExperienceGreaterThan(3).forEach(System.out::println);

        //figure
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(5);

        Triangle triangle = new Triangle();
        triangle.setColor("black");
        triangle.setArea(15);

        circleDao.save(circle);
        triangleDao.save(triangle);
        circleDao.findByColor("red", Circle.class).forEach(System.out::println);
    }
}
