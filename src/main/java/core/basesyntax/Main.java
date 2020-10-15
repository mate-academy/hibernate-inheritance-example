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

public class Main {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoImpl(HibernateUtil.getSessionFactory());
        Person person = new Person();
        person.setName("Person");
        person.setAge(99);
        personDao.save(person);

        Mentor mentor = new Mentor();
        mentor.setAge(27);
        mentor.setName("Ivan");
        personDao.save(mentor);
        Mentor mentor2 = new Mentor();
        mentor2.setAge(23);
        mentor2.setName("Maria");
        personDao.save(mentor2);
        MentorDao mentorDao = new MentorDaoImpl(HibernateUtil.getSessionFactory());
        mentorDao.findByAgeGreaterThan(25).forEach(System.out::println);

        Coach coach = new Coach();
        coach.setAge(33);
        coach.setName("Vasil");
        coach.setExperience(10);
        coach.setTrack(Coach.Track.JAVA);
        CoachDao coachDao = new CoachDaoImpl(HibernateUtil.getSessionFactory());
        coachDao.save(coach);
        Coach coach2 = new Coach();
        coach2.setAge(30);
        coach2.setName("Petro");
        coach2.setExperience(3);
        coach2.setTrack(Coach.Track.JAVA);
        coachDao.save(coach2);
        coachDao.findByExperienceGreaterThan(5).forEach(System.out::println);

        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        Animal animal1 = new Animal();
        animal1.setName("Murchik");
        animal1.setAge(2);
        animalDao.save(animal1);

        Animal animal2 = new Dog();
        animal2.setName("Reks");
        animal2.setAge(3);
        animalDao.save(animal2);

        Animal animal3 = new Cat();
        animal3.setName("Mars");
        animal3.setAge(3);
        animalDao.save(animal3);

        Animal animal4 = new Animal();
        animal4.setName("Murzik");
        animal4.setAge(2);
        animalDao.save(animal4);

        animalDao.findByNameFirstLetter('M').forEach(System.out::println);

        MachineDao machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());
        Machine machine = new Machine();
        machine.setYear(2020);
        machine.setMaker("Peugeot");
        machineDao.save(machine);

        Car machine2 = new Car();
        machine2.setYear(2020);
        machine2.setMaker("Mazda");
        machine2.setModel("CX5");
        machine2.setHorsePower(250);
        machineDao.save(machine2);

        Truck machine3 = new Truck();
        machine3.setYear(2010);
        machine3.setMaker("Nissan");
        machine3.setColor("white");
        machine3.setMaxAllowedWeight(5000);
        machineDao.save(machine3);

        machineDao.findByAgeOlderThan(5).forEach(System.out::println);

        Triangle triangle = new Triangle();
        triangle.setArea(5);
        triangle.setColor("red");

        FigureDao figureDao = new FigureDaoImpl(HibernateUtil.getSessionFactory());
        figureDao.save(triangle);

        Circle circle = new Circle();
        circle.setRadius(3);
        circle.setColor("green");
        figureDao.save(circle);

        figureDao.findByColor("red", Triangle.class).forEach(System.out::println);
    }
}
