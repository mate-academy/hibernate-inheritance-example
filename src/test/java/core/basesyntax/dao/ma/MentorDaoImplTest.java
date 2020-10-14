package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MentorDaoImplTest extends AbstractTest {
    private MentorDao mentorDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        mentorDao = new MentorDaoImpl(getSessionFactory());
    }

    @Test
    public void findByAgeGreaterThan_Ok() {
        Mentor sofia = new Mentor();
        sofia.setAge(20);
        Mentor roman = new Mentor();
        sofia.setAge(22);
        mentorDao.save(sofia);
        mentorDao.save(roman);
        List<Mentor> mentors = mentorDao.findByAgeGreaterThan(20);
        Assert.assertEquals(1, mentors.size());
    }
}
