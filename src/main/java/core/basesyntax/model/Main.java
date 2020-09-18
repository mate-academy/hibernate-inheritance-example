package core.basesyntax.model;

import core.basesyntax.model.embeddable.NewsPost;
import core.basesyntax.model.embeddable.dao.NewsPostDao;
import core.basesyntax.model.embeddable.dao.NewsPostDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.figure.dao.CircleDao;
import core.basesyntax.model.figure.dao.TriangleDao;
import core.basesyntax.model.figure.dao.impl.CircleDaoImpl;
import core.basesyntax.model.figure.dao.impl.TriangleDaoImpl;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.dao.CoachDao;
import core.basesyntax.model.ma.dao.MentorDao;
import core.basesyntax.model.ma.dao.impl.CoachDaoImpl;
import core.basesyntax.model.ma.dao.impl.MentorDaoImpl;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.dao.MachineDao;
import core.basesyntax.model.machine.dao.impl.MachineDaoImpl;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.dao.AnimalDao;
import core.basesyntax.model.zoo.dao.impl.AnimalDaoImpl;

public class Main {
    public static void main(String[] args) {
        AnimalDao animalDao = new AnimalDaoImpl();
        for (Animal animal : DataProvider.getAnimals()) {
            animalDao.save(animal);
        }
        animalDao.findByFirstLetter('A').forEach(System.out::println);
        MachineDao machineDao = new MachineDaoImpl();
        for (Machine machine : DataProvider.getMachines()) {
            machineDao.save(machine);
        }
        machineDao.getOlderThan(4).forEach(System.out::println);

        CoachDao coachDao = new CoachDaoImpl();
        for (Coach coach : DataProvider.getCoaches()) {
            coachDao.save(coach);
        }
        MentorDao mentorDao = new MentorDaoImpl();
        for (Mentor mentor : DataProvider.getMentors()) {
            mentorDao.save(mentor);
        }
        coachDao.getWithExperienceMoreThan(5).forEach(System.out::println);
        mentorDao.getOlderThan(20).forEach(System.out::println);

        CircleDao circleDao = new CircleDaoImpl();
        for (Circle circle : DataProvider.getCircles()) {
            circleDao.save(circle);
        }

        TriangleDao triangleDao = new TriangleDaoImpl();
        for (Triangle triangle : DataProvider.getTriangles()) {
            triangleDao.save(triangle);
        }
        circleDao.getByColor("red").forEach(System.out::println);
        triangleDao.getByColor("red").forEach(System.out::println);

        NewsPostDao postDao = new NewsPostDaoImpl();
        for (NewsPost post : DataProvider.getPosts()) {
            postDao.save(post);
        }
        postDao.getWithMetadataLargerThan(10000);
    }
}
