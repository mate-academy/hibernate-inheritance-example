package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        Circle circle = new Circle();
        circle.setRadius(15);
        circle.setColor("blue");

        FigureDao<Circle> figureDao = new FigureDaoImpl<>(sessionFactory);
        figureDao.save(circle);
        System.out.println(figureDao.findByColor("blue", Circle.class));

        Coach coach = new Coach();
        coach.setName("Bella");
        coach.setAge(23);
        coach.setExperience(5);
        coach.setTrack(Coach.Track.JAVA);

        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.save(coach);
        System.out.println(coachDao.findByExperienceGreaterThan(6));

        Mentor mentor = new Mentor();
        mentor.setName("Alice");
        mentor.setAge(26);

        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        System.out.println(mentorDao.findByAgeGreaterThan(24));

        Car car = new Car();
        car.setModel("Toyota Camry");
        car.setYear(1998);
        car.setHorsePower(90);

        Truck truck = new Truck();
        truck.setColor("black");
        truck.setYear(1950);
        truck.setMaxAllowedWeight(200);

        MachineDao machineDao = new MachineDaoImpl(sessionFactory);
        machineDao.save(car);
        machineDao.save(truck);
        System.out.println(machineDao.findByAgeOlderThan(50));

        Cat biba = new Cat();
        biba.setName("Biba");
        biba.setAge(6);
        biba.setNumberOfLives(9);
        biba.setColor("ginger");

        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        fluffy.setAge(4);
        fluffy.setNumberOfLives(9);
        fluffy.setColor("black");

        Dog dog = new Dog();
        dog.setName("Boba");
        dog.setAge(3);
        dog.setTailLength(10);
        dog.setOwner("Bella");

        Animal hamster = new Animal();
        hamster.setName("belle");
        hamster.setAge(2);

        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        animalDao.save(biba);
        animalDao.save(fluffy);
        animalDao.save(dog);
        animalDao.save(hamster);
        System.out.println(List.of(animalDao.findByNameFirstLetter('B')));
    }
}
