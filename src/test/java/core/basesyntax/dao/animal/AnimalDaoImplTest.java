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
                Cat.class,
                Dog.class
        };
    }

    @Before
    public void setUp() throws Exception {
        animalDao = new AnimalDaoImpl(getSessionFactory());
    }

    @Test
    public void createAnimal_Ok() {
        Animal animal = new Animal();
        animal.setName("Bull Dog");
        animal.setAge(5);
        Animal actual = animalDao.save(animal);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCat_Ok() {
        Cat cat = new Cat();
        cat.setName("Myrka");
        cat.setAge(3);
        cat.setNumberOfLives(9);
        cat.setColor("black");
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createDog_Ok() {
        Dog dog = new Dog();
        dog.setName("Tysik");
        dog.setAge(5);
        dog.setOwner("Alica");
        dog.setTailLength(15);
        Animal actual = animalDao.save(dog);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByLetter_Ok() {
        Animal animal = new Animal();
        animal.setName("cat");
        animalDao.save(animal);
        List<Animal> animals = animalDao.findByNameFirstLetter('c');
        Assert.assertEquals(animals.get(0), animal);
    }
}
