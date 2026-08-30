package core.basesyntax;

import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.ma.PersonDao;
import core.basesyntax.dao.ma.PersonDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;

public class Main {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        AnimalDao animalDao = new AnimalDaoImpl(sessionFactory);
        Animal animal = new Animal();
        animal.setAge(4);
        animal.setName("Barsik");
        animalDao.save(animal);
        Dog dog = new Dog(3,"Bobik",20, "Bob");
        System.out.println(animalDao.save(dog));
        Cat cat = new Cat(2,"Myr4ik",9,"Black");
        System.out.println(animalDao.save(cat));
        List<Animal> animalList = animalDao.findByNameFirstLetter('B');
        animalList.forEach(System.out::println);
        System.out.println(animalDao.getId(2L));

        FigureDao<Figure> figureDao = new FigureDaoImpl<>(sessionFactory);
        Circle redCircle = new Circle("RED", 10);
        figureDao.save(redCircle);
        Triangle blueTriangle = new Triangle("BLUE", 50);
        figureDao.save(blueTriangle);
        Circle blueCircle = new Circle("BLUE", 5);
        figureDao.save(blueCircle);
        List<Figure> figureList = figureDao.findByColor("BLUE", Figure.class);
        figureList.forEach(System.out::println);
        FigureDao<Circle> circleFigureDao = new FigureDaoImpl<>(sessionFactory);
        System.out.println(circleFigureDao.getId(1L, Circle.class));
        FigureDao<Triangle> triangleFigureDao = new FigureDaoImpl<>(sessionFactory);
        System.out.println(triangleFigureDao.getId(1L, Triangle.class));

        Person person = new Person();
        person.setAge(30);
        person.setName("Bob");
        PersonDao personDao = new PersonDaoImpl(sessionFactory);
        personDao.save(person);
        Mentor mentor = new Mentor(32,"Alice");
        personDao.save(mentor);
        Coach coach = new Coach(45,"Jon",5, Coach.Track.QA);
        System.out.println(personDao.save(coach));
        MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
        System.out.println(mentorDao.findByAgeGreaterThan(25));
        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        System.out.println(coachDao.findByExperienceGreaterThan(4));
    }
}
