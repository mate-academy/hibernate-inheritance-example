package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void findByExperienceGreaterThan_Ok() {
        Coach bogdan = new Coach();
        bogdan.setExperience(7);
        Coach stepan = new Coach();
        stepan.setExperience(2);
        coachDao.save(bogdan);
        coachDao.save(stepan);
        List<Coach> coaches = coachDao.findByExperienceGreaterThan(5);
        Assert.assertEquals(1, coaches.size());
    }
}
