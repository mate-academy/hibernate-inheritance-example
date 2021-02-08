package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao<Triangle> figureDao;
    
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Circle.class,
                Triangle.class
        };
    }
    
    @Before
    public void setUp() throws Exception {
        figureDao = new FigureDaoImpl<>(getSessionFactory());
    }
    
    @Test
    public void save() {
        Triangle triangle = new Triangle();
        triangle.setColor("green");
        Triangle save = figureDao.save(triangle);
        Assert.assertNotNull(save);
        Assert.assertNotNull(save.getId());
    }
    
    @Test
    public void selectByColor() {
        Triangle circle = new Triangle();
        circle.setColor("green");
        figureDao.save(circle);
        Triangle triangle = new Triangle();
        triangle.setColor("red");
        figureDao.save(triangle);
        List<Triangle> red = figureDao.findByColor("red", Triangle.class);
        Assert.assertNotNull(red.get(0));
    }
}
