package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class FigureDaoImplTest extends AbstractTest {
    private static final int FIRST = 0;
    private FigureDao figureDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Circle.class,
                Triangle.class,
        };
    }

    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setRadius(7);
        circle.setColor("green");
        Circle actual = (Circle) figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setArea(15);
        triangle.setColor("green");
        Triangle actual = (Triangle) figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findTriangleByColor() {
        Triangle triangle = new Triangle();
        triangle.setArea(15);
        triangle.setColor("green");
        figureDao.save(triangle);
        List<Triangle> triangleList = figureDao.findByColor("green", Triangle.class);
        Assert.assertEquals(triangleList.get(FIRST), triangle);
    }

    @Test
    public void findCircleByColor() {
        Circle circle = new Circle();
        circle.setRadius(7);
        circle.setColor("green");
        figureDao.save(circle);
        List<Circle> circleList = figureDao.findByColor("green", Circle.class);
        Assert.assertEquals(circleList.get(FIRST), circle);
    }
}
