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
    private Dog pitbull;
    private Cat cat_lord;

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
        pitbull = new Dog();
        pitbull.setOwner("Jack");
        pitbull.setTailLength(2);
        pitbull.setAge(5);
        pitbull.setName("Bee");

        cat_lord = new Cat();
        cat_lord.setNumberOfLives(1);
        cat_lord.setColor("Red");
        cat_lord.setAge(5);
        cat_lord.setName("Shinka");
    }

    @Test
    public void createAndGetByLetterAnimals() {
        animalDao.save(pitbull);
        animalDao.save(cat_lord);
        Assert.assertEquals(animalDao.findByNameFirstLetter('B').size(), 1);
        Assert.assertEquals(animalDao.findByNameFirstLetter('S').size(), 1);
    }
}
