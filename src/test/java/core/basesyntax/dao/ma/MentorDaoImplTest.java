package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MentorDaoImplTest extends AbstractTest {
    private MentorDao mentorDao;
    private PersonDao personDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Person.class,
                Coach.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        mentorDao = new MentorDaoImpl(getSessionFactory());
        personDao = new PersonDaoImpl(getSessionFactory());
    }

    @Test
    public void get_list_of_mentors_test() {
        Mentor mentor = new Mentor();
        mentor.setAge(15);
        mentor.setName("Valik");
        Mentor mentor2 = new Mentor();
        mentor2.setAge(13);
        mentor2.setName("Malik");
        personDao.save(mentor);
        personDao.save(mentor2);
        mentorDao.findByAgeGreaterThan(10);
        Assert.assertNotNull(mentorDao.findByAgeGreaterThan(10));
        Assert.assertEquals(2, mentorDao.findByAgeGreaterThan(10).size());
    }
}

