package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;

public class Main {
    public static void main(String[] args) {
        AnimalDao animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        Animal animal = new Animal();
        animal.setName("Barboss");
        animalDao.save(animal);
        Cat cat = new Cat();
        cat.setColor("red");
        cat.setNumberOfLives(9);
        cat.setName("Mursik");
        cat.setAge(3);
        animal.setAge(2);
        animalDao.save(cat);
        Dog dog = new Dog();
        dog.setName("Tusik");
        dog.setAge(5);
        dog.setTailLength(15);
        dog.setOwner("Billy");
        animalDao.save(dog);
        animalDao.findByNameFirstLetter('M').forEach(System.out::println);
    }
}
