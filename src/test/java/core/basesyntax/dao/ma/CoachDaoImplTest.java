package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CoachDaoImplTest extends AbstractTest {
    private CoachDao coachDao;
    private PersonDao personDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Person.class,
                Coach.class
        };
    }

    @Before
    public void setUp() throws Exception {
        coachDao = new CoachDaoImpl(getSessionFactory());
        personDao = new PersonDaoImpl(getSessionFactory());
    }

    @Test
    public void findOneCoachByExperience_Ok() {
        Coach bean = new Coach();
        bean.setAge(27);
        bean.setName("Tiabeanie");
        bean.setExperience(5);
        bean.setTrack(Coach.Track.JAVA);
        Person actual = personDao.save(bean);
        List<Coach> eligibleCoaches = coachDao.findByExperienceGreaterThan(4);
        Assert.assertEquals(eligibleCoaches.get(0).getId(), actual.getId());
    }

    @Test
    public void findMultipleCoachesByExperience_Ok() {
        Coach luci = new Coach();
        luci.setAge(5027);
        luci.setName("Luci");
        luci.setExperience(29);
        luci.setTrack(Coach.Track.JAVA);
        personDao.save(luci);
        Coach elfo = new Coach();
        elfo.setAge(31);
        elfo.setName("Elfo");
        elfo.setExperience(12);
        elfo.setTrack(Coach.Track.UI);
        personDao.save(elfo);
        List<Coach> eligibleCoaches = coachDao.findByExperienceGreaterThan(10);
        Assert.assertEquals(eligibleCoaches.size(), 2);
    }

    @Test
    public void findCoachesByExperience_Fail() {
        try {
            coachDao.findByExperienceGreaterThan(100);;
        } catch (RuntimeException e) {
            Assert.assertEquals("Failed to get all coaches with more than 100 years of experience.", e.getMessage());
            return;
        }
    }
}
