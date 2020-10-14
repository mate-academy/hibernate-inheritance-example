package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CoachDaoImplTest extends AbstractTest {
    private CoachDao coachDao;

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
        coachDao = new CoachDaoImpl(getSessionFactory());
    }

    @Test
    public void createCoach_Ok() {
        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("John");
        Person actual = coachDao.save(coach);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByExperienceGreaterThan_Ok() {
        Coach coach = new Coach();
        coach.setAge(25);
        coach.setName("David");
        coach.setExperience(2);
        coachDao.save(coach);
        List<Coach> coachList = coachDao.findByExperienceGreaterThan(1);
        Assert.assertEquals(coachList.get(0), coach);
    }
}
