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
    private FigureDao<Figure> figureDao;
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Circle.class,
                Triangle.class
        };
    }

    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl(getSessionFactory());
    }

    @Test
    public void addCircle_Ok() {
        Figure circle = new Circle();
        circle.setColor("blue");
        Figure actual = figureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void addTriangle_Ok() {
        Figure triangle = new Triangle();
        triangle.setColor("red");
        Figure actual = figureDao.save(triangle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void findByColor_Ok() {
        FigureDao<Circle> localDao = new FigureDaoImpl<>(getSessionFactory());
        Circle yellowCircle = new Circle();
        yellowCircle.setColor("yellow");
        Circle greenCircle = new Circle();
        greenCircle.setColor("green");
        Circle redCircle = new Circle();
        redCircle.setColor("red");
        Circle secondRedCircle = new Circle();
        secondRedCircle.setColor("red");
        localDao.save(yellowCircle);
        localDao.save(greenCircle);
        localDao.save(redCircle);
        localDao.save(secondRedCircle);

        List<Circle> actual = localDao.findByColor("red", Circle.class);
        Assert.assertNotNull(actual);
        Assert.assertEquals(2, actual.size());
    }
}
