package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void get_list_of_mentors_test() {
        Coach coach = new Coach();
        coach.setName("Bogdan");
        coach.setAge(28);
        coach.setTrack(Coach.Track.JAVA);
        coach.setExperience(5);
        coachDao.save(coach);

        System.out.println(coachDao.findByExperienceGreaterThan(4));
        Assert.assertNotNull(coachDao.findByExperienceGreaterThan(4));
        Assert.assertEquals(1, coachDao.findByExperienceGreaterThan(4).size());
    }
}
