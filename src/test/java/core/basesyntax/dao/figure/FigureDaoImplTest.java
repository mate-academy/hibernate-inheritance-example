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
                Triangle.class,
                Circle.class
        };
    }

    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Test
    public void add_circle_test_ok() {
        Circle circle = new Circle();
        circle.setColor("red");
        figureDao.save(circle);
        Assert.assertNotNull(circle);
        Assert.assertNotNull(circle.getId());
        Assert.assertEquals(1L, circle.getId().longValue());
    }

    @Test
    public void add_triangle_test_ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("red");
        figureDao.save(triangle);
        Assert.assertNotNull(triangle);
        Assert.assertNotNull(triangle.getId());
        Assert.assertEquals(1L, triangle.getId().longValue());

    }

    @Test
    public void get_list_of_triangles_by_year() {
        Triangle triangle = new Triangle();
        triangle.setColor("red");
        figureDao.save(triangle);
        Circle circle = new Circle();
        circle.setColor("red");
        figureDao.save(circle);
        Assert.assertNotNull(figureDao.findByColor("red", Triangle.class));
        Assert.assertEquals(1, figureDao.findByColor("red", Triangle.class).size());
    }

    @Test
    public void get_list_of_circules_by_year() {
        Triangle triangle = new Triangle();
        triangle.setColor("red");
        figureDao.save(triangle);
        Circle circle = new Circle();
        circle.setColor("red");
        figureDao.save(circle);
        Assert.assertNotNull(figureDao.findByColor("red", Circle.class));
        Assert.assertEquals(1, figureDao.findByColor("red", Circle.class).size());
    }
}
