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
        figureDao = new FigureDaoImpl<>(getSessionFactory());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setArea(10.0);
        triangle.setColor("green");
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setRadius(2);
        circle.setColor("green");
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByColor_Ok() {
        Triangle greenTriangle = new Triangle();
        greenTriangle.setColor("green");
        Circle greenCircle = new Circle();
        greenCircle.setColor("green");
        Circle blueCircle = new Circle();
        blueCircle.setColor("blue");
        figureDao.save(greenTriangle);
        figureDao.save(greenCircle);
        figureDao.save(blueCircle);
        List<Figure> greenFigures = figureDao.findByColor("green", Figure.class);
        Assert.assertEquals(2, greenFigures.size());
    }
}
