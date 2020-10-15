package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.SessionFactory;
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
    public void setUp() {
        SessionFactory factory  = HibernateUtil.getSessionFactory();
        animalDao = new AnimalDaoImpl(factory);
    }

    @Test
    public void createAnimal_Ok() {
        Animal animal = new Animal();
        animal.setName("Gruty");
        animal.setAge(400);
        Animal actual = animalDao.save(animal);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
