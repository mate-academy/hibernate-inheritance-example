package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Mentor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class MentorDaoImplTest extends AbstractTest {
    private MentorDao mentorDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Mentor.class
        };
    }

    @Before
    public void setUp() {
        mentorDao = new MentorDaoImpl(getSessionFactory());
    }

    @Test
    public void findByAgeGreaterThan_Ok() {
        Mentor mentor = new Mentor();
        mentor.setName("Bob");
        mentor.setAge(24);
        Mentor mentorDB = (Mentor) mentorDao.save(mentor);

        Mentor mentor1 = new Mentor();
        mentor1.setName("Alisa");
        mentor1.setAge(20);
        Mentor mentor1DB = (Mentor) mentorDao.save(mentor1);

        Mentor mentor2 = new Mentor();
        mentor2.setName("Fill");
        mentor2.setAge(18);
        Mentor mentor2DB = (Mentor) mentorDao.save(mentor2);

        List<Mentor> actual = mentorDao.findByAgeGreaterThan(19);
        List<Mentor> expected = List.of(mentorDB, mentor1DB);
        Assert.assertEquals(actual, expected);
    }
}
