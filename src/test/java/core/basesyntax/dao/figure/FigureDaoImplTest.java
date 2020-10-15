package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao<Circle> dao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Figure.class,
                Circle.class,
                Triangle.class
        };
    }

    @Before
    public void setUp() throws Exception {
        dao = new FigureDaoImpl<>(getSessionFactory());
    }

    @Test
    public void findByColorTest() {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(15);
        dao.save(circle);
        Assert.assertNotNull(circle);
        Assert.assertEquals(1L, circle.getId().longValue());

        circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(25);
        dao.save(circle);
        Assert.assertNotNull(circle);
        Assert.assertEquals(2L, circle.getId().longValue());

        List<Circle> circles = dao.findByColor("red", Circle.class);
        Assert.assertEquals(1, circles.size());
    }
}
