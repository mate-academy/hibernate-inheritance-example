package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnimalDaoImplTest extends AbstractTest {
    private AnimalDao animalDao;
    private static final Character CHARACTER_B = 'B';
    private static final Character CHARACTER_C = 'C';
    private static final Character CHARACTER_A = 'A';

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
    public void createCat_ok() {
        Animal cat = new Cat(3, "black");
        cat.setName("Bars");
        cat.setAge(5);
        animalDao.save(cat);
        assertNotNull(cat);
        assertNotNull(cat.getId());
        assertEquals(1L, cat.getId().longValue());
    }

    @Test
    public void createDog_ok() {
        Animal dog = new Dog(1, "John");
        dog.setName("Anger");
        dog.setAge(5);
        animalDao.save(dog);
        System.out.println(dog);
        assertNotNull(dog);
        assertNotNull(dog.getId());
        assertEquals(1L, dog.getId().longValue());
    }

    @Test
    public void findByLetter_ok() {
        Animal bars = new Cat(3, "black");
        bars.setName("Bars");
        bars.setAge(5);
        animalDao.save(bars);
        Animal banger = new Dog(1, "John");
        banger.setName("Banger");
        banger.setAge(2);
        animalDao.save(banger);
        Animal ars = new Cat(1, "white");
        ars.setName("Ars");
        ars.setAge(8);
        animalDao.save(ars);
        List<Animal> animals = new ArrayList<>();
        animals.add(bars);
        animals.add(banger);
        assertEquals(animalDao.findByNameFirstLetter(CHARACTER_B), animals);
        animals.clear();
        animals.add(ars);
        assertEquals(animalDao.findByNameFirstLetter(CHARACTER_A),animals);
    }

    @Test
    public void findByLetter_negative() {
        Animal cat = new Cat(3, "black");
        cat.setName("Bars");
        cat.setAge(5);
        animalDao.save(cat);
        Animal dog = new Dog(1, "John");
        dog.setName("Banger");
        dog.setAge(5);
        animalDao.save(dog);
        assertTrue(animalDao.findByNameFirstLetter(CHARACTER_C).size() == 0);
    }
}