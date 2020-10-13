package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

public class CoachDaoImplTest extends AbstractTest {
    private CoachDao coachDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Coach.class
        };
    }

    @Before
    public void setUp() throws Exception {
        coachDao = new CoachDaoImpl(getSessionFactory());
    }

    @Test
    public void findByExperienceGreaterThan10_Ok() {
        Coach skilledCoach = new Coach();
        skilledCoach.setExperience(16);
        coachDao.save(skilledCoach);
        Coach notVerySkilledCoach = new Coach();
        notVerySkilledCoach.setExperience(0);
        coachDao.save(notVerySkilledCoach);
        List<Coach> actual = coachDao.findByExperienceGreaterThan(10);
        List<Coach> expected = new ArrayList<>();
        expected.add(skilledCoach);
        Assert.assertTrue(actual.size() == 1);
        Assert.assertTrue(actual.get(0).equals(skilledCoach));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void findByExperienceGreaterThan10_Fail() {
        Coach notVerySkilledCoach = new Coach();
        notVerySkilledCoach.setExperience(0);
        coachDao.save(notVerySkilledCoach);
        List<Coach> actual = coachDao.findByExperienceGreaterThan(10);
        Assert.assertTrue(actual.get(0) != null);
    }
}
