package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MentorDaoImplTest extends AbstractTest {
    private MentorDao mentorDao;

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
        mentorDao = new MentorDaoImpl(getSessionFactory());
        Mentor mentor1 = new Mentor();
        mentor1.setAge(21);
        mentor1.setName("Alice");
        mentorDao.save(mentor1);
        Mentor mentor2 = new Mentor();
        mentor2.setAge(31);
        mentor2.setName("Bob");
        mentorDao.save(mentor2);
        Mentor mentor3 = new Mentor();
        mentor3.setAge(15);
        mentor3.setName("Charlie");
        mentorDao.save(mentor3);
    }

    @Test
    public void mentorFindByAgeAll_Ok() {
        List<Mentor> actual = mentorDao.findByAgeGreaterThan(14);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.get(0).getId());
        Assert.assertEquals(3, actual.size());
    }

    @Test
    public void mentorFindByAgeOlder20_Ok() {
        List<Mentor> actual = mentorDao.findByAgeGreaterThan(20);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.get(0).getId());
        Assert.assertEquals(2, actual.size());
    }
}
