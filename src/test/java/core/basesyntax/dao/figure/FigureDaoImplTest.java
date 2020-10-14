package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.zoo.Animal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;


public class FigureDaoImplTest extends AbstractTest {
    private FigureDao figureDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Circle.class,
                Figure.class,
                Triangle.class
        };
    }

    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(13);
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("green");
        triangle.setArea(25.25);
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findCircleByColor_Ok() {
        Circle circle = new Circle();
        circle.setColor("red");
        figureDao.save(circle);
        List<Animal> figureList = figureDao.findByColor("red", Circle.class);
        Assert.assertEquals(figureList.get(0), circle);
    }

    @Test
    public void findTriangleByColor_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("black");
        figureDao.save(triangle);
        List<Animal> figureList = figureDao.findByColor("black", Triangle.class);
        Assert.assertEquals(figureList.get(0), triangle);
    }
}
