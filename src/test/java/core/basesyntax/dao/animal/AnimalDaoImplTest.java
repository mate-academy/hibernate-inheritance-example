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
    private Dog corgi;
    private Cat british;

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
        corgi = new Dog();
        corgi.setOwner("Tom");
        corgi.setTailLength(2);
        corgi.setAge(5);
        corgi.setName("Corgi");

        british = new Cat();
        british.setNumberOfLives(1);
        british.setColor("Black");
        british.setAge(5);
        british.setName("Lapa");
    }

    @Test
    public void createAndGetByLetterAnimals() {
        animalDao.save(corgi);
        animalDao.save(british);
        Assert.assertEquals(animalDao.findByNameFirstLetter('C').size(), 1);
        Assert.assertEquals(animalDao.findByNameFirstLetter('L').size(), 1);
    }
}
