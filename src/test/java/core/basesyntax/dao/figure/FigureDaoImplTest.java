package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao<Circle> circleFigureDao;
    private FigureDao<Triangle> triangleFigureDao;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Figure.class,
                Circle.class,
                Triangle.class
        };
    }

    @Before
    public void setUp() {
        circleFigureDao = new FigureDaoImpl<>(getSessionFactory());
        triangleFigureDao = new FigureDaoImpl<>(getSessionFactory());
    }

    @Test
    public void createCircle_Ok() {
        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(5);
        Circle actual = circleFigureDao.save(circle);
        Assert.assertNotNull(actual);
        Assert.assertNotNull(actual.getId());
        Assert.assertEquals(1L, actual.getId().longValue());
    }

    @Test
    public void createTriangle_Ok() {
        Triangle triangle = new Triangle();
        triangle.setColor("white");
        triangle.setArea(15);
        Triangle triangleDB = triangleFigureDao.save(triangle);
        Assert.assertNotNull(triangleDB);
        Assert.assertNotNull(triangleDB.getId());
        Assert.assertEquals(1L, triangleDB.getId().longValue());
    }

    @Test
    public void findByColor_Ok() {

        Circle circle = new Circle();
        circle.setColor("blue");
        circle.setRadius(5);
        Circle circleDB = circleFigureDao.save(circle);

        Circle circle2 = new Circle();
        circle2.setColor("grey");
        circle2.setRadius(60);
        Circle circle2DB = circleFigureDao.save(circle2);

        Triangle triangle = new Triangle();
        triangle.setColor("white");
        triangle.setArea(30);
        Triangle triangleDB = triangleFigureDao.save(triangle);

        Triangle triangle2 = new Triangle();
        triangle2.setColor("grey");
        triangle2.setArea(15);
        Triangle triangle2DB = triangleFigureDao.save(triangle2);

        Triangle triangle3 = new Triangle();
        triangle3.setColor("blue");
        triangle3.setArea(30);
        Triangle triangle3DB = triangleFigureDao.save(triangle3);

        List<Circle> actualCircleBlue = circleFigureDao.findByColor("grey", Circle.class);
        List<Circle> expectedCircleBlue = List.of(circle2DB);
        Assert.assertEquals(actualCircleBlue, expectedCircleBlue);

        List<Triangle> actualTriangleWhite = triangleFigureDao.findByColor("white", Triangle.class);
        List<Triangle> expectedTriangleWhite = List.of(triangleDB);
        Assert.assertEquals(actualTriangleWhite, expectedTriangleWhite);
    }
}
