import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        Animal animal1 = new Animal();
        animal1.setName("Cho-cho");
        animal1.setAge(1);
        animalDao.save(animal1);
        Dog dog = new Dog();
        dog.setAge(3);
        dog.setName("Bobik");
        dog.setOwner("nature");
        animalDao.save(dog);
        Cat cat = new Cat();
        cat.setAge(1);
        cat.setName("Barsik");
        cat.setNumberOfLives(9);
        cat.setColor("white");
        animalDao.save(cat);
        List<Animal> animalList = animalDao.findByNameFirstLetter('b');
        for (Animal animal : animalList) {
            System.out.println(animal);
        }

        FigureDao<Circle> circleDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
        Circle circle1 = new Circle();
        circle1.setRadius(15);
        circle1.setColor("red");
        circleDao.save(circle1);
        Circle circle2 = new Circle();
        circle2.setColor("blue");
        circle2.setRadius(11);
        circleDao.save(circle2);
        Circle circle3 = new Circle();
        circle3.setColor("red");
        circle3.setRadius(9);
        circleDao.save(circle3);
        List<Circle> redCircle = circleDao.findByColor("red", Circle.class);
        for (Circle circle : redCircle) {
            System.out.println(circle);
        }

        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
        Triangle triangle1 = new Triangle();
        triangle1.setArea(36);
        triangle1.setColor("blue");
        triangleDao.save(triangle1);
        Triangle triangle2 = new Triangle();
        triangle2.setArea(27);
        triangle2.setColor("red");
        triangleDao.save(triangle2);
        Triangle triangle3 = new Triangle();
        triangle3.setArea(36);
        triangle3.setColor("blue");
        triangleDao.save(triangle3);
        List<Triangle> blueTriangle = triangleDao.findByColor("blue", Triangle.class);
        for (Triangle circle : blueTriangle) {
            System.out.println(circle);
        }

        MachineDao machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());
        Machine machine1 = new Machine();
        machine1.setMaker("VAZ");
        machine1.setYear(1999);
        machineDao.save(machine1);
        Machine machine2 = new Car();
        machine2.setYear(2008);
        machine2.setMaker("Cherry");
        machineDao.save(machine2);
        Car car = new Car();
        car.setMaker("VW");
        car.setModel("Golf GTI");
        car.setHorsePower(230);
        car.setYear(2013);
        machineDao.save(car);
        Truck truck = new Truck();
        truck.setColor("white");
        truck.setMaker("Volvo");
        truck.setYear(2017);
        truck.setMaxAllowedWeight(36000);
        machineDao.save(truck);
        List<Machine> machineList = machineDao.findByAgeOlderThan(2015);
        for (Machine machine : machineList) {
            System.out.println(machine);
        }
    }
}
