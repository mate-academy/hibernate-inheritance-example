package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnimalDaoImplTest extends AbstractTest {
    private AnimalDao animalDao;

    @Before
    public void setUp() throws Exception {
        animalDao = new AnimalDaoImpl(getSessionFactory());
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Animal.class,
                Dog.class,
                Cat.class
        };
    }

    @Test
    public void createAnimal_Ok() {
        Animal animal = new Animal();
        animal.setAge(1);
        animal.setName("fish");
        Animal actual = animalDao.save(animal);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCat_Ok() {
        Animal cat = new Cat();
        cat.setAge(2);
        cat.setName("cat");
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createDog_Ok() {
        Animal dog = new Animal();
        dog.setAge(3);
        dog.setName("dog");
        Animal actual = animalDao.save(dog);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByLetterF_Ok() {
        Animal fish = new Animal();
        fish.setName("fish");
        animalDao.save(fish);
        Animal cat = new Cat();
        cat.setName("cat");
        animalDao.save(cat);
        List<Animal> actual = animalDao.findByNameFirstLetter('f');
        List<Animal> expected = new ArrayList<>();
        expected.add(fish);
        Assert.assertTrue(actual.size() == 1);
        Assert.assertTrue(actual.get(0).equals(fish));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void findByLetterF_Fail() {
        Animal dog = new Animal();
        dog.setName("dog");
        animalDao.save(dog);
        Animal cat = new Cat();
        cat.setName("cat");
        animalDao.save(cat);
        List<Animal> actual = animalDao.findByNameFirstLetter('f');
        Assert.assertTrue(actual.get(0) != null);
    }
}
