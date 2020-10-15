package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.zoo.Animal;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao figureDao;

    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Figure.class,
                Circle.class,
                Triangle.class
        };
    }

    @Test
    public void createFigure_Fail() {
        Figure figure = new Figure();
        figure.setColor("red");
        try {
            Figure actual = figureDao.save(figure);
        } catch (RuntimeException e) {
            Assert.assertEquals("Couldn't save figure " + figure, e.getMessage());
        }
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(11);
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("yellow");
        triangle.setArea(100);
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByBlueColor_Ok() {
        Circle blueCircle = new Circle();
        blueCircle.setColor("blue");
        figureDao.save(blueCircle);
        Circle redCircle = new Circle();
        redCircle.setColor("red");
        figureDao.save(redCircle);
        List<Circle> actual = figureDao.findByColor("blue", Circle.class);
        List<Circle> expected = new ArrayList<>();
        expected.add(blueCircle);
        Assert.assertTrue(actual.size() == 1);
        Assert.assertTrue(actual.get(0).equals(blueCircle));
    }

    @Test (expected = IndexOutOfBoundsException.class)
    public void findByBlueColor_Fail() {
        Circle yellowCircle = new Circle();
        yellowCircle.setColor("yellow");
        figureDao.save(yellowCircle);
        Circle redCircle = new Circle();
        redCircle.setColor("red");
        figureDao.save(redCircle);
        List<Circle> actual = figureDao.findByColor("blue", Circle.class);
        Assert.assertTrue(actual.get(0) != null);
    }
}
