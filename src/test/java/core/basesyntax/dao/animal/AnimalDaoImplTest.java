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
        return new Class[] {
                Animal.class,
                Dog.class,
                Cat.class
        };
    }

    @Before
    public void setUp() throws Exception {
        animalDao = new AnimalDaoImpl(getSessionFactory());
    }

    @Test
    public void createAnimal_Ok() {
        Animal animal = new Animal();
        animal.setName("Pet Cheetah");
        animal.setAge(12);
        Animal actual = animalDao.save(animal);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createDog_Ok() {
        Dog dog = new Dog();
        dog.setName("Dylan");
        dog.setAge(3);
        dog.setOwner("Nastia & Amir");
        dog.setTailLength(60);
        Animal actual = animalDao.save(dog);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCat_Ok() {
        Cat cat = new Cat();
        cat.setName("Kosmos");
        cat.setAge(1);
        cat.setNumberOfLives(9);
        cat.setColor("grey");
        Animal actual = animalDao.save(cat);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
