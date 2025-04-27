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
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        Cat barsik = new Cat(5,"Barsik", 9, "white");
        Dog beem = new Dog(7, "Beem", 50, "Vova");
        animalDao.save(barsik);
        animalDao.save(beem);
        animalDao.findByNameFirstLetter('b');
        animalDao.findByNameFirstLetter('c');

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        Car bmw = new Car(2200, "Germany", 550, "X5");
        Truck volvo = new Truck(2019, "Sweden", "red", 25000);
        machineDao.save(bmw);
        machineDao.save(volvo);
        machineDao.findByAgeOlderThan(2015);
        machineDao.findByAgeOlderThan(2024);

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        Mentor olena = new Mentor(20, "Olena");
        Coach taras = new Coach(25, "Taras",8, Coach.Track.JAVA);
        personDao.save(olena);
        personDao.save(taras);

        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        mentorDao.findByAgeGreaterThan(18);
        mentorDao.findByAgeGreaterThan(30);

        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.findByExperienceGreaterThan(18);
        coachDao.findByExperienceGreaterThan(30);

        FigureDao figureDao = new FigureDaoImpl(sessionFactory);
        Circle circle = new Circle("red", 25);
        Triangle triangle = new Triangle("black", 36);
        figureDao.save(circle);
        figureDao.save(triangle);
        figureDao.findByColor("red", Circle.class);
        figureDao.findByColor("white", Circle.class);
        figureDao.findByColor("black", Triangle.class);
        figureDao.findByColor("green", Triangle.class);

    }
}
