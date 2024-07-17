package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.ma.PersonDaoImpl;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        var animalDao = new AnimalDaoImpl(sessionFactory);
        Dog dog = new Dog(2, "Jek", 40, "Orest");
        Cat cat = new Cat(5, "Olaf", 9, "Black");
        animalDao.save(dog);
        animalDao.save(cat);
        System.out.println(animalDao.findByNameFirstLetter('O'));

        var machineDao = new MachineDaoImpl(sessionFactory);
        Machine machine = new Machine(2022, "BMW");
        Car car = new Car(2023, "Audi", 230, "RS7");
        Truck truck = new Truck(2024, "Volvo", "White", 40000);
        machineDao.save(car);
        machineDao.save(truck);
        machineDao.save(machine);
        System.out.println(machineDao.findByAgeOlderThan(2023));

        var personDao = new PersonDaoImpl(sessionFactory);
        Coach coach = new Coach(5, Coach.Track.JAVA, "Philip", 25);
        Mentor mentor = new Mentor(Mentor.Track.QA, "Orest", 30);
        personDao.save(coach);
        personDao.save(mentor);
        var mentorDao = new MentorDaoImpl(sessionFactory);
        System.out.println(mentorDao.findByAgeGreaterThan(25));
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        System.out.println(coachDao.findByExperienceGreaterThan(4));

        var figureDao = new FigureDaoImpl(sessionFactory);
        Circle circle = new Circle("Black", 16);
        Triangle triangle = new Triangle("Black", 100);
        figureDao.save(circle);
        figureDao.save(triangle);
        System.out.println(figureDao.findByColor("Black", Circle.class));
    }
}
