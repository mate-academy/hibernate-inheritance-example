package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import org.hibernate.MappingException;
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
    public void createFigure_Fail() {
        Figure figure = new Figure();
        figure.setColor("red");
        try {
            figureDao.save(figure);
        } catch (RuntimeException e) {
            Assert.assertEquals("Failed to add the figure to the DB.", e.getMessage());
            return;
        }
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("green");
        circle.setRadius(11);
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("black");
        triangle.setArea(100);
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }
}
