package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CoachDaoImplTest extends AbstractTest {
    private CoachDao dao;
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
        dao = new CoachDaoImpl(getSessionFactory());
    }

    @Test
    public void coachDaoTest() {
        Coach coach = new Coach();
        coach.setAge(20);
        coach.setName("John");
        coach.setExperience(2);
        coach.setTrack(Coach.Track.FE);
        dao.save(coach);
        coach = new Coach();
        coach.setAge(20);
        coach.setExperience(5);
        coach.setName("Bohdan");
        coach.setTrack(Coach.Track.JAVA);
        dao.save(coach);

        List<Coach> coachList = dao.findByExperienceGreaterThan(2);
        Assert.assertEquals(1, coachList.size());
        Assert.assertEquals(2L, coachList.get(0).getId().longValue());
    }
}
