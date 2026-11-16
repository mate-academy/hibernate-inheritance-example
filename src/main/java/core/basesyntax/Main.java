package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.ma.PersonDao;
import core.basesyntax.dao.ma.PersonDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
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
        Cat cat = new Cat();
        cat.setColor("black");
        cat.setAge(12);
        cat.setNumberOfLives(8);
        cat.setName("Murka");

        Dog dog = new Dog();
        dog.setOwner("Inna");
        dog.setAge(15);
        dog.setName("bobik");
        dog.setAge(13);

        Animal animal = new Animal();
        animal.setAge(11);
        animal.setName("Bob");

        Truck truck = new Truck();
        truck.setColor("red");
        truck.setMaxAllowedWeight(1000);
        truck.setMaker("Volvo");
        truck.setYear(2012);

        Car car = new Car();
        car.setHorsePower(200);
        car.setModel("Leopard");
        car.setMaker("Nissan");
        car.setYear(1980);

        Machine machine = new Machine();
        machine.setMaker("BMW");
        machine.setYear(1999);

        Person person = new Person();
        person.setAge(35);
        person.setName("Inna");

        Coach coach = new Coach();
        coach.setExperience(10);
        coach.setTrack(Coach.Track.JAVA);
        coach.setAge(40);
        coach.setName("Lola");

        Mentor mentor = new Mentor();
        mentor.setAge(24);
        mentor.setName("Bob");

        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(cat);
        animalDao.save(dog);
        animalDao.save(animal);
        System.out.println(animalDao.findByNameFirstLetter('B'));

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(truck);
        machineDao.save(car);
        machineDao.save(machine);
        System.out.println(machineDao.findByAgeOlderThan(15));

        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        personDao.save(person);
        personDao.save(coach);
        personDao.save(mentor);
        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        System.out.println(mentorDao.findByAgeGreaterThan(20));
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        System.out.println(coachDao.findByExperienceGreaterThan(9));
    }
}
