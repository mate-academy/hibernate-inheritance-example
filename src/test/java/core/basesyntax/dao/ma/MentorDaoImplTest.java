package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class MentorDaoImplTest extends AbstractTest {
    private Mentor r_mentor;
    private Mentor v_mentor;
    private MentorDao mentorDao;
    private PersonDao personDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Person.class,
                Mentor.class
        };
    }

    @Before
    public void before() {
        mentorDao = new MentorDaoImpl(getSessionFactory());
        personDao = new PersonDaoImpl(getSessionFactory());
        r_mentor = new Mentor(25, "Roman");
        v_mentor = new Mentor(30, "Vasya");
    }

    @Test
    public void findByAgeGreaterThanOk() {
        personDao.save(r_mentor);
        personDao.save(v_mentor);
        List<Mentor> mentors = mentorDao.findByAgeGreaterThan(26);
        Assert.assertEquals(mentors.get(0).getName(), "Vasya");
    }

    @Test
    public void findByAgeGreaterThanEmptyResultList() {
        personDao.save(r_mentor);
        personDao.save(v_mentor);
        List<Mentor> mentors = mentorDao.findByAgeGreaterThan(30);
        Assert.assertEquals(mentors.size(), 0);
    }
}
