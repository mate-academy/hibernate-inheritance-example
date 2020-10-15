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
    public void animalDaoSaveCat_Ok(){
        Animal cat = new Cat();
        cat.setName("Doggo");
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
        Assert.assertEquals("Doggo", actual.getName());
    }

    @Test
    public void animalDaoSaveDog_Ok(){
        Animal dog = new Dog();
        dog.setName("Cato");
        Animal actual = animalDao.save(dog);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
        Assert.assertEquals("Cato", actual.getName());
    }

    @Test
    public void animalFindByFirstLetter_Ok(){
        Animal dog = new Dog();
        dog.setName("Jimmy");
        Animal cat = new Cat();
        cat.setName("Diego");
        animalDao.save(dog);
        animalDao.save(cat);
        List<Animal> actual = animalDao.findByNameFirstLetter('J');
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.get(0).getId());
        Assert.assertEquals(1, actual.size());
    }
}
