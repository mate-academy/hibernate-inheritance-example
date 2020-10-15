package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class AnimalDaoImplTest extends AbstractTest {
    private AnimalDao animalDao;
    private Dog dog;
    private Cat cat;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Animal.class,
                Cat.class,
                Dog.class
        };
    }

    @Before
    public void setUp() {
        animalDao = new AnimalDaoImpl(getSessionFactory());
        cat = new Cat();
        cat.setName("Sirko");
        cat.setNumberOfLives(9);
        cat.setColor("gray");
        cat.setAge(3);

        dog = new Dog();
        dog.setAge(4);
        dog.setName("Barsik");
        dog.setOwner("Petro");
        dog.setTailLength(20);
    }

    @Test
    public void createCat() {
        animalDao.save(cat);
        Assert.assertEquals(animalDao.getAll().size(), 1);
    }

    @Test
    public void createDog() {
        animalDao.save(dog);
        Assert.assertEquals(animalDao.getAll().size(), 1);
    }

    @Test
    public void getDogByFirstNameLetter() {
        animalDao.save(dog);
        animalDao.save(cat);
        List<Animal> animals = animalDao.findByNameFirstLetter('B');
        Assert.assertEquals(animals.get(0).getName(), "Barsik");
    }

    @Test
    public void getCatByFirstNameLetter() {
        animalDao.save(dog);
        animalDao.save(cat);
        List<Animal> animals = animalDao.findByNameFirstLetter('S');
        Assert.assertEquals(animals.get(0).getName(), "Sirko");
    }
}
