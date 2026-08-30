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
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        Animal animal = new Animal();
        animal.setAge(100);
        animal.setName("Yeti");

        Cat cat = new Cat();
        cat.setAge(3);
        cat.setName("Pussy");
        cat.setColor("black");
        cat.setNumberOfLives(9);

        Dog dog = new Dog();
        dog.setName("Yukon");
        dog.setAge(7);
        dog.setOwner("Bob");
        dog.setTailLength(30);

        AnimalDao animalDao = new AnimalDaoImpl(factory);
        System.out.println(animalDao.save(animal));
        System.out.println(animalDao.save(cat));
        System.out.println(animalDao.save(dog));
        animalDao.findByNameFirstLetter('Y').forEach(System.out::println);
        animalDao.findByNameFirstLetter('P').forEach(System.out::println);

        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(10);

        FigureDao<Circle> circleDao = new FigureDaoImpl<>(factory);
        System.out.println(circleDao.save(circle));
        circleDao.findByColor("red", Circle.class).forEach(System.out::println);

        Triangle triangle = new Triangle();
        triangle.setColor("red");
        triangle.setArea(100);

        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(factory);
        System.out.println(triangleDao.save(triangle));
        triangleDao.findByColor("red", Triangle.class).forEach(System.out::println);

        Machine machine = new Machine();
        machine.setMaker("AutoZAZ");
        machine.setYear(2014);

        Car car = new Car();
        car.setMaker("Volvo");
        car.setModel("v50");
        car.setYear(2007);
        car.setHorsePower(100);

        Truck truck = new Truck();
        truck.setMaker("Daimler AG");
        truck.setColor("grey");
        truck.setYear(2017);
        truck.setMaxAllowedWeight(60);

        MachineDao machineDao = new MachineDaoImpl(factory);
        System.out.println(machineDao.save(machine));
        System.out.println(machineDao.save(car));
        System.out.println(machineDao.save(truck));
        machineDao.findByAgeOlderThan(8).forEach(System.out::println);

        Person person = new Person();
        person.setName("Bob");
        person.setAge(25);

        PersonDao personDao = new PersonDaoImpl(factory);
        System.out.println(personDao.save(person));

        Mentor mentor = new Mentor();
        mentor.setName("Alice");
        mentor.setAge(21);

        MentorDao mentorDao = new MentorDaoImpl(factory);
        System.out.println(mentorDao.save(mentor));
        mentorDao.findByAgeGreaterThan(20).forEach(System.out::println);

        Coach coach = new Coach();
        coach.setName("Tom");
        coach.setAge(32);
        coach.setExperience(10);
        coach.setTrack(Coach.Track.JAVA);

        CoachDao coachDao = new CoachDaoImpl(factory);
        System.out.println(coachDao.save(coach));
        coachDao.findByExperienceGreaterThan(8).forEach(System.out::println);
    }
}
