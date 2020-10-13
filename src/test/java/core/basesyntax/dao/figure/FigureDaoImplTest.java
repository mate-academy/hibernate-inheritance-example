package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao figureDao;

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
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("blue");
        triangle.setArea(22.3);
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(12);
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
