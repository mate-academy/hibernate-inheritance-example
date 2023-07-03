import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.util.HibernateUtil;

public class Main {
    public static void main(String[] args) {
        FigureDao<Figure> figureDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());

        Triangle triangle = new Triangle();
        triangle.setColor("yellow");
        triangle.setArea(30);
        figureDao.save(triangle);

        Circle circle = new Circle();
        circle.setColor("red");
        circle.setRadius(20);
        figureDao.save(circle);

    }
}
