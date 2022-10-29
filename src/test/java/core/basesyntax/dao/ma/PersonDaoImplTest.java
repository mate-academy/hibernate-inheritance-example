package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class PersonDaoImplTest extends AbstractTest {
    private PersonDao personDao;
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Person.class,
                Coach.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        personDao = new PersonDaoImpl(getSessionFactory());
    }

    @Test
    public void getPersonGrater_Ok() {
        Person person = new Person();
        person.setAge(15);
        person.setName("Bob");
        Person personActual = personDao.save(person);
        Assert.assertNotNull(personActual);
        Assert.assertNotNull(personActual.getId());
        Assert.assertEquals(1L, personActual.getId().longValue());

        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("John");
        Mentor mentor = new Mentor();
        mentor.setAge(25);
        mentor.setName("Alice");

        Person mentorActual = personDao.save(mentor);
        Assert.assertNotNull(mentorActual);
        Assert.assertNotNull(mentorActual.getId());
        Assert.assertEquals(2L, mentorActual.getId().longValue());
        Person coachActual = personDao.save(coach);
        Assert.assertNotNull(coachActual);
        Assert.assertNotNull(coachActual.getId());
        Assert.assertEquals(3L, coachActual.getId().longValue());

        List<Person> byAgeGreater = personDao.findByAgeGreater(18);
        Assert.assertEquals(2, byAgeGreater.size());
    }

    @Test
    public void createPerson_Ok() {
        Person person = new Person();
        person.setAge(20);
        person.setName("Bob");
        Person actual = personDao.save(person);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCoach_Ok() {
        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("John");
        Person actual = personDao.save(coach);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createMentor_Ok() {
        Mentor mentor = new Mentor();
        mentor.setAge(20);
        mentor.setName("Alice");
        Person actual = personDao.save(mentor);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
