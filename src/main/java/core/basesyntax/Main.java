package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        SessionFactory factory = new Configuration().configure().buildSessionFactory();

        var cat = new Cat();
        cat.setName("Catty");
        cat.setAge(20);
        cat.setNumberOfLives(9);
        cat.setColor("Black");
        AnimalDao animalDao = new AnimalDaoImpl(factory);
        animalDao.save(cat);

        var dog = new Dog();
        dog.setName("Bobby");
        dog.setAge(20);
        dog.setOwner("Bob");
        dog.setTailLength(10);
        animalDao.save(dog);

        animalDao
                .findByNameFirstLetter('C')
                .forEach(animal -> System.out.println("Found animal: " + animal.getName()));

        var car = new Car();
        car.setYear(2022);
        car.setMaker("Toyota");
        car.setHorsePower(200);
        car.setModel("Camry");
        MachineDao machineDao = new MachineDaoImpl(factory);
        machineDao.save(car);

        Truck truck = new Truck();
        truck.setYear(2015);
        truck.setMaker("Ford");
        truck.setColor("White");
        truck.setMaxAllowedWeight(8.200);
        machineDao.save(truck);

        machineDao
                .findByAgeOlderThan(3)
                .forEach(machine -> System.out.println("Found machine: " + machine.getMaker()));

        Mentor mentor = new Mentor();
        mentor.setName("John");
        mentor.setAge(52);
        MentorDao mentorDao = new MentorDaoImpl(factory);
        mentorDao.save(mentor);

        mentorDao.findByAgeGreaterThan(50)
                .forEach(m -> System.out.println("Found mentor: " + m.getName()));

        Circle circle = new Circle();
        circle.setRadius(5);
        circle.setColor("White");
        FigureDao<Circle> circleDao = new FigureDaoImpl<>(factory);
        circleDao.save(circle);

        Triangle triangle = new Triangle();
        triangle.setColor("Black");
        triangle.setArea(50.5);
        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(factory);
        triangleDao.save(triangle);

        circleDao.findByColor("White", Circle.class)
                .forEach(c -> System.out.println("Found circle: " + c.getColor()));

        triangleDao.findByColor("Black", Triangle.class)
                .forEach(t -> System.out.println("Found triangle: " + t.getColor()));

        factory.close();
    }
}
