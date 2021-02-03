package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractTest;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;

public class FigureDaoImplTest extends AbstractTest {
    private FigureDao figureDao;
    @Override
    protected Class<?>[] entities() {
        return new Class[] {
                Figure.class,
                Circle.class,
                Triangle.class
        };
    }


}
