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
        return new Class[] {
                Person.class,
                Coach.class,
                Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        coachDao = new CoachDaoImpl(getSessionFactory());
        Coach coach1 = new Coach();
        coach1.setExperience(7);
        coach1.setName("Alice");
        coachDao.save(coach1);
        Coach coach2 = new Coach();
        coach2.setExperience(2);
        coach2.setName("Bob");
        coachDao.save(coach2);
        Coach coach3 = new Coach();
        coach3.setExperience(1);
        coach3.setName("Charlie");
        coachDao.save(coach3);
    }

    @Test
    public void coachFindByExperienceAll_Ok() {
        List<Coach> actual = coachDao.findByExperienceGreaterThan(0);
        Assert.assertNotNull(actual);
        Assert.assertEquals(3, actual.size());
    }

    @Test
    public void coachFindByMoreThan5_Ok() {
        List<Coach> actual = coachDao.findByExperienceGreaterThan(5);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1, actual.size());
    }

}
