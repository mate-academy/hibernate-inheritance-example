package core.basesyntax.util;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
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
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        FigureDao<Figure> figureDao = new FigureDaoImpl<>(sessionFactory);
        Circle firstCircle = new Circle();
        firstCircle.setRadius(20);
        figureDao.save(firstCircle);

        Triangle firstTriangle = new Triangle();
        firstTriangle.setArea(40);
        figureDao.save(firstTriangle);

        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        Animal bull = new Animal();
        bull.setName("Bull");
        animalDao.save(bull);

        Dog buddy = new Dog();
        buddy.setName("buddy");
        animalDao.save(buddy);

        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        animalDao.save(fluffy);

        Dog bob = new Dog();
        bob.setName("Bob");
        animalDao.save(bob);

        System.out.println(animalDao.findByNameFirstLetter('B'));

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        Person person = new Person();
        person.setAge(30);
        person.setName("Bohdan");
        personDao.save(person);

        Coach coach = new Coach();
        coach.setExperience(2);
        coach.setTrack(Coach.Track.FE);
        personDao.save(coach);

        Mentor mentor = new Mentor();
        personDao.save(mentor);

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        Machine volvo = new Machine();
        volvo.setYear(2019);
        volvo.setMaker("volvoMaker");
        machineDao.save(volvo);

        Truck truck = new Truck();
        truck.setColor("red");
        truck.setMaxAllowedWeight(500);
        machineDao.save(truck);

        Car renault = new Car();
        renault.setModel("renault");
        renault.setHorsePower(200);
        machineDao.save(renault);
    }
}
