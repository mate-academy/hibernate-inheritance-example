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
import core.basesyntax.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    private static final FigureDao<Figure> figureDao = new FigureDaoImpl<>(sessionFactory);
    private static final AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
    private static final CoachDao coachDao = new CoachDaoImpl(sessionFactory);
    private static final MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
    private static final PersonDao personDao = new PersonDaoImpl(sessionFactory);
    private static final MachineDao machineDao = new MachineDaoImpl(sessionFactory);

    public static void main(String[] args) {
        circle();
        machine();
        person();
        animal();

    }
    static void animal(){
        Cat cat = new Cat();
        cat.setColor("black");
        cat.setName("Pushok");
        cat.setAge(3);
        animalDao.save(cat);
        Dog dog = new Dog();
        dog.setName("Reks");
        dog.setAge(5);
        dog.setTailLength(5);
        dog.setOwner("Ivan");
        animalDao.save(dog);
        Animal animal = new Animal();
        animal.setName("Mik");
        animal.setAge(8);
        animalDao.save(animal);


    }
    static void person() {
        Person person = new Person();
        person.setAge(43);
        person.setName("Ivan");

        Coach coach = new Coach();
        coach.setExperience(10);
        coach.setAge(42);
        coach.setTrack(Coach.Track.JAVA);
        coach.setName("Petr");

        Mentor mentor = new Mentor();
        mentor.setAge(22);
        mentor.setName("Anna");
        personDao.save(person);
        mentorDao.save(mentor);
        coachDao.save(coach);
    }

    static void machine() {
        Machine machine = new Machine();
        machine.setYear(2017);
        machine.setMaker("Mitsubishi");

        Car car = new Car();
        car.setYear(2018);
        car.setMaker("Daihatsyu");
        car.setModel("Terious");
        car.setHorsePower(75);

        Car car2 = new Car();
        car2.setYear(2022);
        car2.setMaker("Opel");
        car2.setModel("Astra");
        car2.setHorsePower(175);

        Truck truck = new Truck();
        truck.setColor("orange");
        truck.setYear(2019);
        truck.setMaker("CAT");
        truck.setMaxAllowedWeight(202.55);

        machineDao.save(machine);
        machineDao.save(car);
        machineDao.save(truck);
        machineDao.save(car2);

        final List<Machine> byAgeOlderThan = machineDao.findByAgeOlderThan(2022);
        for (Machine machine1 : byAgeOlderThan) {
            System.out.println(machine1.getId() + " " + machine1.getMaker()
                    + " " + machine1.getYear());
        }
    }

    static void circle() {
        Circle circle = new Circle();
        circle.setRadius(5);
        circle.setColor("black");

        Triangle triangle = new Triangle();
        triangle.setArea(25.5);
        triangle.setColor("white");

        figureDao.save(circle);
        figureDao.save(triangle);
        System.out.println(figureDao.findByColor("black", Figure.class));
    }
}
