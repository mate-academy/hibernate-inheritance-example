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
import core.basesyntax.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {

        Animal elephant = new Animal();
        elephant.setName("Elephant");
        elephant.setAge(5);
        Cat murzik = new Cat();
        murzik.setName("Murzik");
        murzik.setAge(1);
        murzik.setColor("white");
        Cat pushok = new Cat();
        pushok.setName("marsik");
        pushok.setAge(2);
        pushok.setColor("grey");
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(elephant);
        animalDao.save(murzik);
        animalDao.save(pushok);
        List<Animal> byNameFirstLetter = animalDao.findByNameFirstLetter('M');
        System.out.println(byNameFirstLetter);
        System.out.println(byNameFirstLetter.size());

        Machine bus = new Machine();
        bus.setYear(2010);
        bus.setMaker("Ford");
        Car car = new Car();
        car.setModel("Golf");
        car.setHorsePower(160);
        car.setYear(2011);
        car.setMaker("VW");
        Truck truck = new Truck();
        truck.setColor("Orange");
        truck.setMaxAllowedWeight(10);
        truck.setYear(2008);
        truck.setMaker("Volvo");
        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(bus);
        machineDao.save(car);
        machineDao.save(truck);
        System.out.println(machineDao.findByAgeOlderThan(10));

        Person student = new Person();
        student.setAge(20);
        student.setName("Bob");
        Mentor mentor = new Mentor();
        mentor.setAge(30);
        mentor.setName("John");
        Coach coach = new Coach();
        coach.setAge(40);
        coach.setName("Alice");
        coach.setExperience(10);
        coach.setTrack(Coach.Track.JAVA);
        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        personDao.save(student);
        personDao.save(mentor);
        personDao.save(coach);
        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        System.out.println(mentorDao.findByAgeGreaterThan(29));
        System.out.println(coachDao.findByExperienceGreaterThan(9));

        Triangle triangle = new Triangle();
        triangle.setColor("black");
        triangle.setArea(100);
        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(10);
        FigureDao<Triangle> triangleFigureDao = new FigureDaoImpl<>(sessionFactory);
        FigureDao<Circle> circleFigureDao = new FigureDaoImpl<>(sessionFactory);
        triangleFigureDao.save(triangle);
        circleFigureDao.save(circle);
        System.out.println(triangleFigureDao.findByColor("black", Triangle.class));
    }
}
