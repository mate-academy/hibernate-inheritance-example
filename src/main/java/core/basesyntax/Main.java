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
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Circle circleRed = new Circle();
        circleRed.setColor("red");
        circleRed.setRadius(5);

        FigureDao figureDao = new FigureDaoImpl(sessionFactory);
        figureDao.save(circleRed);

        System.out.println(circleRed);
        System.out.println();

        System.out.println(figureDao.findByColor("red", Circle.class));
        System.out.println();

        Triangle triangleBlue = new Triangle();
        triangleBlue.setColor("blue");
        triangleBlue.setArea(9);
        figureDao.save(triangleBlue);
        System.out.println(triangleBlue);
        System.out.println();

        System.out.println(figureDao.findByColor("red", Triangle.class));
        System.out.println();

        Cat cat = new Cat();
        cat.setAge(5);
        cat.setName("Murzick");
        cat.setColor("black");
        cat.setNumberOfLives(9);

        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(cat);

        System.out.println(cat);
        System.out.println();

        Dog dog = new Dog();
        dog.setAge(3);
        dog.setName("Zhuchka");
        dog.setOwner("Alice");
        dog.setTailLength(2);
        animalDao.save(dog);
        System.out.println(dog);
        System.out.println();

        System.out.println(animalDao.findByNameFirstLetter('Z'));
        System.out.println();

        Machine machine = new Machine();
        machine.setMaker("KhTZ");
        machine.setYear(2010);

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(machine);

        System.out.println(machine);
        System.out.println();

        Car slavuta = new Car();
        slavuta.setMaker("ZAZ");
        slavuta.setYear(2015);
        slavuta.setHorsePower(25);
        slavuta.setModel("Slavuta");
        machineDao.save(slavuta);
        System.out.println(slavuta);
        System.out.println();

        Truck kraz = new Truck();
        kraz.setMaker("KrAZ");
        kraz.setYear(2018);
        kraz.setColor("green");
        kraz.setMaxAllowedWeight(5000);
        machineDao.save(kraz);
        System.out.println(kraz);
        System.out.println();

        System.out.println(machineDao.findByAgeOlderThan(7));
        System.out.println();

        Person personBob = new Person();
        personBob.setAge(22);
        personBob.setName("Bob");

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        personDao.save(personBob);

        System.out.println(personBob);
        System.out.println();

        Mentor mentorVasia = new Mentor();
        mentorVasia.setAge(28);
        mentorVasia.setName("Vasia");

        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.save(mentorVasia);

        System.out.println(mentorVasia);
        System.out.println();

        System.out.println(mentorDao.findByAgeGreaterThan(20));
        System.out.println();

        Coach coach = new Coach();
        coach.setAge(30);
        coach.setName("Maxim");
        coach.setExperience(8);
        coach.setTrack(Coach.Track.JAVA);

        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.save(coach);

        System.out.println(coach);
        System.out.println();

        System.out.println(coachDao.findByExperienceGreaterThan(5));
        System.out.println();
    }
}
