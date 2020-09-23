package core.basesyntax.model;

import core.basesyntax.model.embeddable.NewsPost;
import core.basesyntax.model.embeddable.PostMetadata;
import core.basesyntax.model.embeddable.dao.NewsPostDao;
import core.basesyntax.model.embeddable.dao.NewsPostDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.figure.dao.CircleDao;
import core.basesyntax.model.figure.dao.CircleDaoImpl;
import core.basesyntax.model.figure.dao.TriangleDao;
import core.basesyntax.model.figure.dao.TriangleDaoImpl;
import core.basesyntax.model.game.Bowman;
import core.basesyntax.model.game.Character;
import core.basesyntax.model.game.Farmer;
import core.basesyntax.model.game.dao.CharacterDao;
import core.basesyntax.model.game.dao.CharacterDaoImpl;
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
        Cat cat1 = new Cat();
        cat1.setName("c1");
        dao.save(cat1);
        Cat cat2 = new Cat();
        cat2.setName("c2");
        dao.save(cat2);
        Cat cat3 = new Cat();
        cat3.setName("c3");
        dao.save(cat3);
        Cat cat4 = new Cat();
        cat4.setName("c4");
        dao.save(cat4);
        Dog dog1 = new Dog();
        dog1.setName("d1");
        dao.save(dog1);
        Dog dog2 = new Dog();
        dog2.setName("d2");
        dao.save(dog2);
        Dog dog3 = new Dog();
        dog3.setName("cd3");
        dao.save(dog3);
        Dog dog4 = new Dog();
        dog4.setName("d4");
        dao.save(dog4);

        dao.findByNameFirstLetter('c')
                .stream()
                .map(Animal::getName)
                .forEach(System.out::println);
    }

    public static void playWithMachines() {
        MachineDao dao = new MachineDaoImpl();
        Car car1 = new Car();
        car1.setYear(5);
        dao.save(car1);
        Car car2 = new Car();
        car2.setYear(2);
        dao.save(car2);
        Car car3 = new Car();
        car3.setYear(13);
        dao.save(car3);
        Truck t1 = new Truck();
        t1.setYear(10);
        dao.save(t1);
        Truck t2 = new Truck();
        t2.setYear(2);
        dao.save(t2);

        dao.findByAgeOlderThan(4)
                .stream()
                .map(Machine::getYear)
                .forEach(System.out::println);
    }

    public static void playWithPeople() {
        CoachDao dao = new CoachDaoImpl();
        MentorDao mentorDao = new MentorDaoImpl();
        Coach c1 = new Coach();
        c1.setExperience(100);
        dao.save(c1);
        Mentor m1 = new Mentor();
        m1.setAge(19);
        mentorDao.save(m1);
        Mentor m2 = new Mentor();
        m2.setAge(25);
        mentorDao.save(m2);
        dao.findByExperienceGreaterThan(10)
                .stream()
                .map(Coach::getExperience)
                .forEach(System.out::println);
        mentorDao.findByAgeGreaterThan(20)
                .stream()
                .map(Mentor::getAge)
                .forEach(System.out::println);
    }

    public static void playWithCharacters() {
//        CharacterDao dao = new CharacterDaoImpl();
//        Bowman b1 = new Bowman();
//        b1.setPower(200);
//        dao.save(b1);
//        Farmer f1 = new Farmer();
//        f1.setPower(10);
//        dao.save(f1);
//        Farmer f2 = new Farmer();
//        f2.setPower(26);
//        dao.save(f2);
//
//        dao.findAllByPowerAcs()
//                .stream()
//                .map(Character::getPower)
//                .forEach(System.out::println);
    }

    public static void playWithFigures() {
        CircleDao cdao = new CircleDaoImpl();
        TriangleDao tdao = new TriangleDaoImpl();
        Circle c1 = new Circle();
        c1.setColor("red");
        cdao.save(c1);
        Triangle t1 = new Triangle();
        t1.setColor("red");
        tdao.save(t1);

        cdao.findByColor("red").stream().map(Circle::getColor).forEach(System.out::println);
        tdao.findByColor("red").stream().map(Triangle::getColor).forEach(System.out::println);

    }

    public static void playWithNews() {
        NewsPostDao dao = new NewsPostDaoImpl();
        PostMetadata data = new PostMetadata();
        data.setSize(2000);
        NewsPost n1 = new NewsPost();
        n1.setMetadata(data);
        dao.save(n1);

        dao.findBySizeGreaterThan(1000).stream().map(NewsPost::getMetadata).map(PostMetadata::getSize).forEach(System.out::println);

    }
}
