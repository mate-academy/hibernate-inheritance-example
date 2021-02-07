package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Animal cat = new Cat();
        cat.setAge(2);
        cat.setName("cat");
        ((Cat) cat).setColor("black");
        ((Cat) cat).setNumberOfLives(7);
        Animal dog = new Dog();
        dog.setName("dog");
        dog.setAge(3);
        ((Dog) dog).setOwner("Max");
        ((Dog) dog).setTailLength(30);
        AnimalDaoImpl animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        animalDao.save(cat);
        animalDao.save(dog);
        List<Animal> cats = animalDao.findByNameFirstLetter('C');
        System.out.println(cats);
    }
}
