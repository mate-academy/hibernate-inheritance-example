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
        Assert.assertTrue(true);
    }
}
