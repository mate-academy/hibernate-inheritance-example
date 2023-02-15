package core.basesyntax;

import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.SessionFactory;

public class Main {
    private static final SessionFactory factory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        Circle circle = new Circle("yellow", 10);
        new FigureDaoImpl<Circle>(factory).save(circle);
        Triangle triangle = new Triangle("red", 25);
        new FigureDaoImpl<Triangle>(factory).save(triangle);
    }
}
