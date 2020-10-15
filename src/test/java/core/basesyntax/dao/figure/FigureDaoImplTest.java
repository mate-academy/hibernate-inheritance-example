package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class FigureDaoImplTest  extends AbstractTest {
    private FigureDao<Figure> figureDao;
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Triangle.class,
                Circle.class
        };
    }

    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(3);
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle= new Triangle();
        triangle.setColor("blue");
        triangle.setArea(43.8);
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByColor_Ok() {
        FigureDao<Circle> circleDao = new FigureDaoImpl<>(getSessionFactory());
        Circle circle1 = new Circle();
        circle1.setColor("blue");
        circle1.setRadius(3);
        Circle circle2 = new Circle();
        circle2.setColor("red");
        circle2.setRadius(3);
        figureDao.save(circle1);
        figureDao.save(circle2);
        List<Circle> redCircles = circleDao.findByColor("red", Circle.class);
        Assert.assertNotNull(redCircles);
        Assert.assertEquals(1, redCircles.size());
    }

}
