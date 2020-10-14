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
        return new Class[]{
                Person.class,
                Coach.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        mentorDao = new MentorDaoImpl(getSessionFactory());
    }

    @Test
    public void createMentor_Ok() {
        Mentor mentor = new Mentor();
        mentor.setAge(20);
        mentor.setName("Alice");
        Person actual = mentorDao.save(mentor);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByAgeGreaterThan_Ok() {
        Mentor mentor = new Mentor();
        mentor.setName("Anna");
        mentor.setAge(25);
        mentorDao.save(mentor);
        List<Mentor> mentorList = mentorDao.findByAgeGreaterThan(20);
        Assert.assertEquals(1, mentorList.size());
        Assert.assertEquals(mentorList.get(0), mentor);
    }
}
