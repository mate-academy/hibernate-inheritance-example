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
    private AnimalDao animalDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
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
        Cat cat = new Cat();
        cat.setAge(2);
        cat.setName("Cat");
        cat.setColor("Black");
        cat.setNumberOfLives(9);
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByNameFirstLetter_Ok() {
        Cat cat = new Cat();
        cat.setName("Cat");
        Dog dog = new Dog();
        dog.setName("Dog");
        animalDao.save(cat);
        animalDao.save(dog);
        List<Animal> animals = animalDao.findByNameFirstLetter('D');
        Assert.assertEquals(1, animals.size());
    }
}
