package core.basesyntax.model;

import java.util.List;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.model.zoo.dao.CatDao;
import core.basesyntax.model.zoo.dao.DogDao;
import core.basesyntax.model.zoo.dao.impl.CatDaoImpl;
import core.basesyntax.model.zoo.dao.impl.DogDaoImpl;

public class Main {
    public static void main(String[] args) {
        CatDao catDao = new CatDaoImpl();
        for (Cat cat : getCats()) {
            catDao.save(cat);
        }

        DogDao dogDao = new DogDaoImpl();
        for (Dog dog : getDogs()) {
            dogDao.save(dog);
        }

        List<Cat> cats = catDao.findByFirstLetter('A');
        List<Dog> dogs = dogDao.findByFirstLetter('A');
        cats.forEach(System.out::println);
        dogs.forEach(System.out::println);

    }

    private static List<Cat> getCats() {
        return List.of(new Cat("Adam"),
                new Cat("Brad"),
                new Cat("Avn"),
                new Cat("Merlin"),
                new Cat("Vovan"));
    }


    private static List<Dog> getDogs() {
        return List.of(new Dog("April"),
                new Dog("Brad"),
                new Dog("Adamant"),
                new Dog("Merlin"),
                new Dog("Vovan"));
    }

}
