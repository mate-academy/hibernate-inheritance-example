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
        // inheritance example : Single Table
        Animal animal = new Animal();
        animal.setAge(5);
        animal.setName("Barsik");

        Cat cat = new Cat();
        cat.setAge(3);
        cat.setName("Murka");
        cat.setColor("Black");
        cat.setNumberOfLives(9);

        Dog dog = new Dog();
        dog.setAge(7);
        dog.setName("Sharik");
        dog.setOwner("Vasya");
        dog.setTailLength(10);

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(animal);
        animalDao.save(cat);
        animalDao.save(dog);

        System.out.println("Animal name starts with the letter 'M': "
                + animalDao.findByNameFirstLetter('M'));

        // Inheritance example : Table per class
        MachineDao machineDao = new MachineDaoImpl(sessionFactory);

        Machine machine = new Machine(2024, "Toyota");
        machineDao.save(machine);

        Car car = new Car(200, "Civic");
        car.setYear(2015);
        car.setMaker("Honda");
        machineDao.save(car);

        Truck truck = new Truck("Red", 1000);
        truck.setYear(2018);
        truck.setMaker("Volvo");
        machineDao.save(truck);

        System.out.println("Machines older than 5 years: "
                + machineDao.findByAgeOlderThan(5));

        // Inheritance example : Joined
        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        Person person = new Person();
        person.setAge(25);
        person.setName("John");
        personDao.save(person);

        Coach coach = new Coach();
        coach.setAge(55);
        coach.setName("Joel");
        coach.setExperience(15);
        coach.setTrack(Coach.Track.valueOf("FE"));
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        personDao.save(coach);

        System.out.println("Coaches with experience more than 10 years: "
                + coachDao.findByExperienceGreaterThan(10));

        Mentor mentor = new Mentor();
        mentor.setAge(35);
        mentor.setName("Bogdan");
        mentor.setProgrammingLanguage("Java");
        mentor.setInstitute("Mate Academy");

        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.save(mentor);

        System.out.println("Mentors and coaches older than 30: "
                           + mentorDao.findByAgeGreaterThan(30));

        //Inheritance example : Single Table
        FigureDao figureDao = new FigureDaoImpl(sessionFactory);
        Circle circle = new Circle();
        circle.setRadius(5);
        circle.setColor("Red");
        figureDao.save(circle);

        Triangle triangle = new Triangle();
        triangle.setArea(10);
        triangle.setColor("Green");
        figureDao.save(triangle);

        System.out.println("Figures with color 'Red': "
                + figureDao.findByColor("Red", Circle.class));
    }
}
