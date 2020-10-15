package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao<Figure> figureDao;
    private Circle circle;
    private Triangle triangle;

    @Override
    protected Class<?>[] entities() {
        return new Class[]{
                Circle.class,
                Figure.class,
                Triangle.class
        };
    }

    @Before
    public void setUp() {
        figureDao = new FigureDaoImpl(getSessionFactory());
        circle = new Circle();
        circle.setColor("red");
        circle.setRadius(15);

        triangle = new Triangle();
        triangle.setColor("red");
        triangle.setArea(36);
    }
}
