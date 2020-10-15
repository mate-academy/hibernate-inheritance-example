package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AnimalDaoImplTest extends AbstractTest {
    private AnimalDao dao;

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
        dao = new AnimalDaoImpl(getSessionFactory());
    }

    @Test
    public void createCat_Ok() {
        Cat cat = new Cat();
        cat.setAge(4);
        cat.setName("barsik");
        cat.setColor("gray");
        cat.setNumberOfLives(7);
        Cat actual = (Cat) dao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByName_Ok() {
        Dog dog = new Dog();
        dog.setAge(4);
        dog.setName("boris");
        dog.setOwner("boy");
        dog.setTailLength(10);
        dao.save(dog);
        dog = new Dog();
        dog.setAge(4);
        dog.setName("aris");
        dog.setOwner("boy");
        dog.setTailLength(10);
        dao.save(dog);
        List<Animal> animals = dao.findByNameFirstLetter('b');
        Assert.assertEquals(1, animals.size());
        Dog actual = (Dog) animals.get(0);
        Assert.assertEquals("boy", actual.getOwner());
    }

    @Test
    public void findByName_NotOk() {
        Dog dog = new Dog();
        dog.setAge(4);
        dog.setName("boris");
        dog.setOwner("boy");
        dog.setTailLength(10);
        dao.save(dog);
        List<Animal> animals = dao.findByNameFirstLetter('a');
        Assert.assertNotNull(animals);
        Assert.assertEquals(0, animals.size());
    }
}
