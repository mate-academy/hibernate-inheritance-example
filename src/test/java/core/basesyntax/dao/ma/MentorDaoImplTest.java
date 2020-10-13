package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Machine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MentorDaoImplTest extends AbstractTest {
    private MentorDao mentorDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
            Mentor.class
        };
    }

    @Before
    public void setUp() throws Exception {
        mentorDao = new MentorDaoImpl(getSessionFactory());
    }

    @Test
    public void findByAgeGreaterThan50_Ok() {
        Mentor youngMentor = new Mentor();
        youngMentor.setAge(25);
        mentorDao.save(youngMentor);
        Mentor nowYoungMentor = new Mentor();
        nowYoungMentor.setAge(69);
        mentorDao.save(nowYoungMentor);
        List<Mentor> actual = mentorDao.findByAgeGreaterThan(50);
        List<Mentor> expected = new ArrayList<>();
        expected.add(nowYoungMentor);
        Assert.assertTrue(actual.size() == 1);
        Assert.assertTrue(actual.get(0).equals(nowYoungMentor));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void findByAgeGreaterThan50_Fail() {
        Mentor youngMentor = new Mentor();
        youngMentor.setAge(25);
        mentorDao.save(youngMentor);
        List<Mentor> actual = mentorDao.findByAgeGreaterThan(50);
        Assert.assertTrue(actual.get(0) != null);
    }
}
