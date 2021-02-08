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
    public void save() {
        Animal cat = new Cat();
        cat.setName("Milka");
        cat.setAge(100);
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
    
    @Test
    public void findByLetter() {
        Animal cat = new Cat();
        cat.setName("Sima");
        cat.setAge(100);
        animalDao.save(cat);
        Animal dog = new Cat();
        dog.setName("Charly");
        dog.setAge(1);
        Animal animal = animalDao.save(dog);
        List<Animal> actual = animalDao.findByNameFirstLetter('C');
                Assert.assertNotNull(actual.get(0));
                Assert.assertEquals(dog, actual.get(0));
    }
}
