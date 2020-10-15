package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
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
    public void findByExperienceGreaterThan_Ok() {
        Coach coach = new Coach();
        coach.setName("Bob");
        coach.setAge(35);
        coach.setExperience(2);
        coach.setTrack(Coach.Track.JAVA);
        coachDao.save(coach);

        Coach coach1 = new Coach();
        coach1.setName("Alisa");
        coach1.setAge(30);
        coach1.setExperience(4);
        coach1.setTrack(Coach.Track.FE);
        coachDao.save(coach1);

        Coach coach2 = new Coach();
        coach2.setName("Fill");
        coach2.setAge(28);
        coach2.setExperience(6);
        coach2.setTrack(Coach.Track.QA);
        Coach coach2DB = (Coach) coachDao.save(coach2);

        List<Coach> actual = coachDao.findByExperienceGreaterThan(5);
        List<Coach> expected = List.of(coach2DB);
        Assert.assertEquals(expected, actual);
    }
}
