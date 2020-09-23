package core.basesyntax.model;

import core.basesyntax.model.embeddable.NewsPost;
import core.basesyntax.model.embeddable.PostMetadata;
import core.basesyntax.model.embeddable.dao.NewsPostDao;
import core.basesyntax.model.embeddable.dao.NewsPostDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.figure.dao.FigureDao;
import core.basesyntax.model.figure.dao.FigureDaoImpl;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.dao.CoachDao;
import core.basesyntax.model.ma.dao.CoachDaoImpl;
import core.basesyntax.model.ma.dao.MentorDao;
import core.basesyntax.model.ma.dao.MentorDaoImpl;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.machine.dao.MachineDao;
import core.basesyntax.model.machine.dao.MachineDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.model.zoo.dao.AnimalDao;
import core.basesyntax.model.zoo.dao.AnimalDaoImpl;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        playWithAnimals();
        playWithMachines();
        playWithPeople();
        playWithFigures();
        playWithNews();
    }

    public static void playWithAnimals() {
        AnimalDao dao = new AnimalDaoImpl();
        List<String> names = List.of("Alice", "Benny", "Marcy", "Ben", "Amy");
        for (int i = 0; i < 5; i++) {
            Cat cat = new Cat();
            cat.setName(names.get(i) + " cat");
            dao.save(cat);
        }
        for (int i = 0; i < 5; i++) {
            Dog dog = new Dog();
            dog.setName(names.get(i) + " dog");
            dao.save(dog);
        }
        dao.findByNameFirstLetter('A')
                .stream()
                .map(Animal::getName)
                .forEach(System.out::println);
    }

    public static void playWithMachines() {
        MachineDao dao = new MachineDaoImpl();
        for (int i = 0; i < 5; i++) {
            Car car = new Car();
            car.setYear(i * 2);
            dao.save(car);
        }
        for (int i = 0; i < 5; i++) {
            Truck truck = new Truck();
            truck.setYear(i * 2);
            dao.save(truck);
        }
        dao.findByAgeOlderThan(4)
                .stream()
                .map(Machine::getYear)
                .forEach(System.out::println);
    }

    public static void playWithPeople() {
        CoachDao coachDao = new CoachDaoImpl();
        MentorDao mentorDao = new MentorDaoImpl();
        for (int i = 0; i < 3; i++) {
            Coach coach = new Coach();
            coach.setExperience(i * 6);
            coachDao.save(coach);
        }
        for (int i = 0; i < 5; i++) {
            Mentor mentor = new Mentor();
            mentor.setAge(i * 10);
            mentorDao.save(mentor);
        }
        coachDao.findByExperienceGreaterThan(5)
                .stream()
                .map(Coach::getExperience)
                .forEach(System.out::println);
        mentorDao.findByAgeGreaterThan(20)
                .stream()
                .map(Mentor::getAge)
                .forEach(System.out::println);
    }

    public static void playWithFigures() {
        FigureDao<Circle> circleDao = new FigureDaoImpl<>();
        FigureDao<Triangle> triangleDao = new FigureDaoImpl<>();
        List<String> colors = List.of("blue", "white", "red", "yellow", "green");
        for (int i = 0; i < 3; i++) {
            Circle circle = new Circle();
            circle.setColor(colors.get(i));
            circleDao.save(circle);
        }
        for (int i = 0; i < 5; i++) {
            Triangle triangle = new Triangle();
            triangle.setColor(colors.get(i));
            triangleDao.save(triangle);
        }
        Stream.concat(
                circleDao.findByColor("red", Circle.class).stream(),
                triangleDao.findByColor("red", Triangle.class).stream())
                .map(Figure::getColor)
                .forEach(System.out::println);
    }

    public static void playWithNews() {
        NewsPostDao dao = new NewsPostDaoImpl();
        for (int i = 0; i < 5; i++) {
            PostMetadata data = new PostMetadata();
            data.setSize(i * 500);
            NewsPost post = new NewsPost();
            post.setMetadata(data);
            dao.save(post);
        }
        dao.findBySizeGreaterThan(1000)
                .stream()
                .map(NewsPost::getMetadata)
                .map(PostMetadata::getSize)
                .forEach(System.out::println);
    }
}
