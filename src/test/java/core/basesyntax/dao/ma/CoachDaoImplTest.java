package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class CoachDaoImplTest extends AbstractTest {
    private Coach p_coach;
    private Coach b_coach;
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
    public void before() {
        coachDao = new CoachDaoImpl(getSessionFactory());
        personDao = new PersonDaoImpl(getSessionFactory());
        p_coach = new Coach(27, "Pavlo",1, Coach.Track.JAVA);
        b_coach = new Coach(27, "Bogdan",4, Coach.Track.JAVA);
    }

    @Test
    public void testFindExperienceGreaterThanOk() {
        personDao.save(p_coach);
        personDao.save(b_coach);
        List<Coach> coaches = coachDao.findByExperienceGreaterThan(2);
        Assert.assertEquals(coaches.size(), 1);
    }

    @Test
    public void testFindExperienceGreaterThanEmptyResultList() {
        personDao.save(p_coach);
        personDao.save(b_coach);
        List<Coach> coaches = coachDao.findByExperienceGreaterThan(5);
        Assert.assertEquals(coaches.size(), 0);
    }
}
