package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

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
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(15);

        Figure actual = dao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByColorTest() {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(15);
        dao.save(circle);
        circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(25);
        dao.save(circle);

        List<Circle> circles = dao.findByColor("red", Circle.class);
        Assert.assertEquals(1, circles.size());
    }
}
