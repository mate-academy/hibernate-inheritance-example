import core.basesyntax.dao.AbstractTest;
import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.ma.PersonDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
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

/**
 * @author Vadim Okulov, me (edited and implemented test db)
 */

public class Main extends AbstractTest {
    private static final AbstractTest factory = new Main();

    public static void main(String[] args) {
        factory.init();
        singleTable();
        System.out.println("-".repeat(100));
        tablePerClass();
        System.out.println("-".repeat(100));
        joinTables();
        System.out.println("-".repeat(100));
        mappedSuperclass();
    }

    private static void singleTable() {
        Dog dog1 = new Dog();
        dog1.setAge(6);
        dog1.setName("Lisa");
        dog1.setOwner("Vad");
        dog1.setTailLength(25);

        Dog dog2 = new Dog();
        dog2.setAge(10);
        dog2.setName("Masya");
        dog2.setOwner("Sonya");
        dog2.setTailLength(15);

        Dog dog3 = new Dog();
        dog3.setAge(3);
        dog3.setName("Bobik");
        dog3.setOwner("Bob");
        dog3.setTailLength(20);

        Cat cat1 = new Cat();
        cat1.setAge(4);
        cat1.setName("Barsik");
        cat1.setColor("Black");
        cat1.setNumberOfLives(9);

        Cat cat2 = new Cat();
        cat2.setAge(4);
        cat2.setName("Merlyn");
        cat2.setColor("White");
        cat2.setNumberOfLives(6);

        AnimalDao animalDao = new AnimalDaoImpl(factory.getSessionFactory());
        System.out.println("Saved animal: " + animalDao.save(dog1));
        System.out.println("Saved animal: " + animalDao.save(dog2));
        System.out.println("Saved animal: " + animalDao.save(dog3));
        System.out.println("Saved animal: " + animalDao.save(cat1));
        System.out.println("Saved animal: " + animalDao.save(cat2));
        System.out.println("\033[33mAnimals by first letter 'B': ");
        System.out.println(animalDao.findByNameFirstLetter('B'));
        System.out.println("Animals by first letter 'M': ");
        System.out.println(animalDao.findByNameFirstLetter('M') + "\033[0m");
    }

    private static void tablePerClass() {
        Car car1 = new Car();
        car1.setYear(2015);
        car1.setMaker("Volkswagen");
        car1.setModel("Golf");
        car1.setHorsePower(250);

        Car car2 = new Car();
        car2.setYear(2008);
        car2.setMaker("Daewoo");
        car2.setModel("Matiz");
        car2.setHorsePower(53);

        Truck truck1 = new Truck();
        truck1.setYear(2012);
        truck1.setMaker("CAT");
        truck1.setColor("Yellow");
        truck1.setMaxAllowedWeight(15000);

        Truck truck2 = new Truck();
        truck2.setYear(2013);
        truck2.setMaker("Volvo");
        truck2.setColor("Blue");
        truck2.setMaxAllowedWeight(5000);

        MachineDao machineDao = new MachineDaoImpl(factory.getSessionFactory());
        System.out.println("Saved machine: " + machineDao.save(car1));
        System.out.println("Saved machine: " + machineDao.save(car2));
        System.out.println("Saved machine: " + machineDao.save(truck1));
        System.out.println("Saved machine: " + machineDao.save(truck2));
        System.out.println("\033[33mMachines older than 2000 years: ");
        System.out.println(machineDao.findByAgeOlderThan(2000));
        System.out.println("Machines older than 2012 years: ");
        System.out.println(machineDao.findByAgeOlderThan(2012) + "\033[0m");
    }

    private static void joinTables() {
        Coach coach1 = new Coach();
        coach1.setName("Bohdan");
        coach1.setAge(28);
        coach1.setExperience(8);
        coach1.setTrack(Coach.Track.JAVA);

        Coach coach2 = new Coach();
        coach2.setName("Bob");
        coach2.setAge(25);
        coach2.setExperience(5);
        coach2.setTrack(Coach.Track.FE);

        Mentor mentor1 = new Mentor();
        mentor1.setAge(26);
        mentor1.setName("Alice");

        Mentor mentor2 = new Mentor();
        mentor2.setAge(23);
        mentor2.setName("John");

        Mentor mentor3 = new Mentor();
        mentor3.setAge(21);
        mentor3.setName("Bruce");

        PersonDaoImpl personDao = new PersonDaoImpl(factory.getSessionFactory());
        CoachDao coachDao = new CoachDaoImpl(factory.getSessionFactory());
        MentorDao mentorDao = new MentorDaoImpl(factory.getSessionFactory());

        System.out.println("Saved person: " + personDao.save(coach1));
        System.out.println("Saved person: " + personDao.save(coach2));
        System.out.println("Saved person: " + personDao.save(mentor1));
        System.out.println("Saved person: " + personDao.save(mentor2));
        System.out.println("Saved person: " + personDao.save(mentor3));
        System.out.println("\033[33mCoach`s ByExperienceGreaterThan 6 years: ");
        System.out.println(coachDao.findByExperienceGreaterThan(6));
        System.out.println("Mentors ByAgeGreaterThan 22 years: ");
        System.out.println(mentorDao.findByAgeGreaterThan(22) + "\033[0m");
    }

    private static void mappedSuperclass() {
        Circle circle1 = new Circle();
        circle1.setColor("circle");
        circle1.setRadius(25);

        Circle circle2 = new Circle();
        circle2.setColor("circle");
        circle2.setRadius(10);

        Triangle triangle1 = new Triangle();
        triangle1.setColor("triangle");
        triangle1.setArea(10);

        Triangle triangle2 = new Triangle();
        triangle2.setColor("triangle");
        triangle2.setArea(20);

        FigureDao<Figure> figureDao = new FigureDaoImpl<>(factory.getSessionFactory());
        System.out.println("Saved figure: " + figureDao.save(circle1));
        System.out.println("Saved figure: " + figureDao.save(circle2));
        System.out.println("Saved figure: " + figureDao.save(triangle1));
        System.out.println("Saved figure: " + figureDao.save(triangle2));
        System.out.println("\033[33mFigures ByColor 'triangle': ");
        System.out.println(figureDao.findByColor("triangle", Figure.class));
        System.out.println("Figures ByColor 'circle': ");
        System.out.println(figureDao.findByColor("circle", Figure.class) + "\033[0m");
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {Animal.class, Dog.class, Cat.class, Machine.class, Car.class,
                Truck.class, Person.class, Mentor.class, Coach.class, Figure.class,
                Triangle.class, Circle.class};
    }
}
