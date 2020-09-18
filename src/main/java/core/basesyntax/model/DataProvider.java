package core.basesyntax.model;

import java.util.List;
import core.basesyntax.model.embeddable.NewsPost;
import core.basesyntax.model.embeddable.PostMetadata;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;

public class DataProvider {
    public static List<NewsPost> getPosts() {
        return List.of(new NewsPost(new PostMetadata(20000)),
                new NewsPost(new PostMetadata(10000)),
                new NewsPost(new PostMetadata(5000)),
                new NewsPost(new PostMetadata(1000)),
                new NewsPost(new PostMetadata(15000)));
    }

    public static List<Circle> getCircles() {
        return List.of(new Circle("red"),
                new Circle("black"),
                new Circle("green"));
    }

    public static List<Triangle> getTriangles() {
        return List.of(new Triangle("yellow"),
                new Triangle("red"),
                new Triangle("red"));
    }

    public static List<Coach> getCoaches() {
        return List.of(new Coach(6),
                new Coach(3),
                new Coach(4),
                new Coach(10),
                new Coach(8));
    }

    public static List<Mentor> getMentors() {
        return List.of(new Mentor(18),
                new Mentor(20),
                new Mentor(21),
                new Mentor(19),
                new Mentor(23));
    }

    public static List<Machine> getMachines() {
        return List.of(new Car(2015),
                new Car(2020),
                new Car(2010),
                new Car(2012),
                new Car(2009),
                new Truck(2015),
                new Truck(2016),
                new Truck(2021),
                new Truck(2010),
                new Truck(2012));
    }

    public static List<Animal> getAnimals() {
        return List.of(new Cat("Adam"),
                new Cat("Brad"),
                new Cat("Avn"),
                new Cat("Merlin"),
                new Cat("Vovan"),
                new Dog("April"),
                new Dog("Brad"),
                new Dog("Adamant"),
                new Dog("Merlin"),
                new Dog("Vovan"));
    }
}
