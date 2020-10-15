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

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Animal.class,
                Dog.class,
                Cat.class
        };
    }

    @Before
    public void setUp() {
        animalDao = new AnimalDaoImpl(getSessionFactory());
    }

    @Test
    public void createAnimal_Ok() {
        Animal animal = new Animal();
        animal.setAge(2);
        animal.setName("Rex");
        Animal actual = animalDao.save(animal);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createDog_Ok() {
        Dog dog = new Dog();
        dog.setOwner("Vasya");
        dog.setTailLength(20);
        dog.setAge(3);
        dog.setName("Ross");
        Animal actual = animalDao.save(dog);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCat_Ok() {
        Cat cat = new Cat();
        cat.setColor("black");
        cat.setNumberOfLives(5);
        cat.setAge(6);
        cat.setName("Murka");
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void getAnimalByFirstLetter_Ok() {
        Animal animal = new Animal();
        animal.setAge(2);
        animal.setName("Rex");
        Animal animalDB = animalDao.save(animal);

        Dog dog = new Dog();
        dog.setOwner("Vasya");
        dog.setTailLength(20);
        dog.setAge(3);
        dog.setName("Ross");
        Animal dogDB = animalDao.save(dog);

        Dog dog2 = new Dog();
        dog2.setOwner("Vasya");
        dog2.setTailLength(30);
        dog2.setAge(1);
        dog2.setName("Bars");
        Animal dog2DB = animalDao.save(dog2);

        Cat cat = new Cat();
        cat.setColor("black");
        cat.setNumberOfLives(5);
        cat.setAge(6);
        cat.setName("Murka");
        Animal catDB = animalDao.save(cat);

        List<Animal> actualAnimal = animalDao.findByNameFirstLetter('R');
        List<Animal> expectedAnimal = List.of(animalDB, dogDB);
        Assert.assertEquals(expectedAnimal, actualAnimal);

        List<Animal> actualDog = animalDao.findByNameFirstLetter('B');
        List<Animal> expectedDog = List.of(dog2DB);
        Assert.assertEquals(expectedDog, actualDog);

        List<Animal> actualCat = animalDao.findByNameFirstLetter('M');
        List<Animal> expectedCat = List.of(catDB);
        Assert.assertEquals(expectedCat, actualCat);
    }
}
