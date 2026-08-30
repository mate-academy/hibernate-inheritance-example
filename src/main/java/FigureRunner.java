import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureRunner {
    public static void main(String[] args) {
        List<Runnable> runnableList = new ArrayList<>();
        runnableList.add(getRunnable());
        runnableList.forEach(Runnable::run);
    }

    public static Runnable getRunnable() {
        return () -> {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Circle circle = new Circle();
            circle.setColor("Yellow");
            circle.setRadius(10);
            FigureDao<Circle> circleDao = new FigureDaoImpl<>(sessionFactory);
            circleDao.save(circle);

            Triangle triangle = new Triangle();
            triangle.setColor("Blue");
            triangle.setArea(100);
            FigureDao<Triangle> triangleDao = new FigureDaoImpl<>(sessionFactory);
            triangleDao.save(triangle);

            List<Triangle> trianglesList = triangleDao.findByColor("Blue", Triangle.class);
            System.out.println("triangles in list: " + trianglesList.size());
            trianglesList.forEach(System.out::println);
        };
    }
}
