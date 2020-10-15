package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void add_cat_test_ok() {
        Cat cat = new Cat();
        cat.setName("Tom");
        animalDao.save(cat);
        Assert.assertNotNull(cat);
        Assert.assertNotNull(cat.getId());
        Assert.assertEquals(1L, cat.getId().longValue());
    }

    @Test
    public void add_dog_test_ok() {
        Dog dog = new Dog();
        dog.setName("Bob");
        animalDao.save(dog);
        Assert.assertNotNull(dog);
        Assert.assertNotNull(dog.getId());
        Assert.assertEquals(1L, dog.getId().longValue());
    }

    @Test
    public void get_by_first_letter_test() {
        Cat cat = new Cat();
        cat.setName("Tom");
        animalDao.save(cat);
        Dog dog = new Dog();
        dog.setName("Bob");
        animalDao.save(dog);
        Assert.assertNotNull(animalDao.findByNameFirstLetter('T'));
        Assert.assertEquals(1, animalDao.findByNameFirstLetter('T').size());
    }
}
