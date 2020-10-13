package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MentorDaoImplTest extends AbstractTest {
    private static final int AGE_1 = 50;
    private static final int AGE_2 = 30;
    private static final int AGE_3 = 100;
    private MentorDao mentorDao;
    private PersonDao personDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Person.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        mentorDao = new MentorDaoImpl(getSessionFactory());
        personDao = new PersonDaoImpl(getSessionFactory());
    }

    @Test
    public void findOneMentorByAge_Ok() {
        Mentor bojack = new Mentor();
        bojack.setAge(52);
        bojack.setName("Bojack Horseman");
        Person actual = personDao.save(bojack);
        List<Mentor> eligibleCoaches = mentorDao.findByAgeGreaterThan(AGE_1);
        Assert.assertEquals(eligibleCoaches.get(0).getId(), actual.getId());
    }

    @Test
    public void findMultipleCoachesByExperience_Ok() {
        Mentor peanutbutter = new Mentor();
        peanutbutter.setAge(45);
        peanutbutter.setName("Mr Peanutbutter");
        personDao.save(peanutbutter);
        Mentor carolyn = new Mentor();
        carolyn.setAge(31);
        carolyn.setName("Princess Carolyn");;
        personDao.save(carolyn);
        List<Mentor> eligibleMentors = mentorDao.findByAgeGreaterThan(AGE_2);
        Assert.assertEquals(eligibleMentors.size(), 2);
    }

    @Test
    public void findMentorsByExperience_Fail() {
        Mentor bojack = new Mentor();
        bojack.setAge(52);
        bojack.setName("Bojack Horseman");
        Person actual = personDao.save(bojack);
        try {
            mentorDao.findByAgeGreaterThan(AGE_3);;
        } catch (RuntimeException e) {
            Assert.assertEquals("Failed to get all mentors older than " + AGE_3, e.getMessage());
            return;
        }
    }
}