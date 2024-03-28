import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.dao.animal.AnimalDaoImpl;
import core.basesyntax.dao.figure.FigureDao;
import core.basesyntax.dao.figure.FigureDaoImpl;
import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.dao.machine.MachineDao;
import core.basesyntax.dao.machine.MachineDaoImpl;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.machine.Car;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.Truck;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.Dog;
import java.time.LocalDate;
import java.util.List;
import util.HibernateUtil;

public class Main {
    public static final String PINK_COLOR = "Pink";
    public static final String RED_COLOR = "Red";
    private static FigureDao<Figure> figureDao;
    private static AnimalDao animalDao;
    private static FigureDao<Circle> circleDao;
    private static FigureDao<Triangle> triangleDao;
    private static CoachDao coachDao;
    private static MentorDao mentorDao;
    private static MachineDao machineDao;

    public static void main(String[] args) {
        figureDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
        animalDao = new AnimalDaoImpl(HibernateUtil.getSessionFactory());
        circleDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
        triangleDao = new FigureDaoImpl<>(HibernateUtil.getSessionFactory());
        coachDao = new CoachDaoImpl(HibernateUtil.getSessionFactory());
        mentorDao = new MentorDaoImpl(HibernateUtil.getSessionFactory());
        machineDao = new MachineDaoImpl(HibernateUtil.getSessionFactory());

        //1)MappedSuperclass
        Circle circle = new Circle();
        circle.setColor(PINK_COLOR);
        circle.setRadius(5);
        Triangle triangle = new Triangle();
        triangle.setColor(RED_COLOR);
        triangle.setArea(10);
        figureDao.save(circle);
        System.out.println("Saved circle: " + circle);
        figureDao.save(triangle);
        System.out.println("Saved triangle: " + triangle);
        Circle redCircle = new Circle();

        redCircle.setColor(RED_COLOR);
        Circle pinkCircle = new Circle();
        pinkCircle.setColor(PINK_COLOR);
        Circle secondPinkCircle = new Circle();
        secondPinkCircle.setColor(PINK_COLOR);

        figureDao.save(redCircle);
        figureDao.save(pinkCircle);
        figureDao.save(secondPinkCircle);
        List<Circle> pinkCircles = circleDao.findByColor(PINK_COLOR, Circle.class);
        System.out.println(pinkCircles);

        Triangle redTriangle = new Triangle();
        redTriangle.setColor(RED_COLOR);
        Triangle pinkTriangle = new Triangle();
        pinkTriangle.setColor(PINK_COLOR);
        Triangle secondPinkTriangle = new Triangle();
        secondPinkTriangle.setColor(PINK_COLOR);

        figureDao.save(redTriangle);
        figureDao.save(pinkTriangle);
        figureDao.save(secondPinkTriangle);

        List<Triangle> pinkTriangles = triangleDao.findByColor(PINK_COLOR, Triangle.class);
        System.out.println(pinkTriangles);

        //2)Single table
        Dog bob = new Dog();
        bob.setName("Bob");
        Dog buddy = new Dog();
        buddy.setName("buddy");
        Cat fluffy = new Cat();
        fluffy.setName("Fluffy");
        Animal bull = new Animal();
        bull.setName("Bull");
        animalDao.save(bob);
        animalDao.save(buddy);
        animalDao.save(bull);
        animalDao.save(fluffy);
        List<Animal> animalsByNameFirstLetter = animalDao.findByNameFirstLetter('B');
        System.out.println(animalsByNameFirstLetter.size());
        animalsByNameFirstLetter.forEach(System.out::println);

        Coach coach = new Coach();
        coach.setName("Coach");
        coach.setAge(30);
        coach.setExperience(3);
        Coach secondCoach = new Coach();
        secondCoach.setName("SecondCoach");
        secondCoach.setAge(28);
        secondCoach.setExperience(2);
        coachDao.save(coach);
        coachDao.save(secondCoach);
        List<Coach> coaches = coachDao.findByExperienceGreaterThan(2);
        System.out.println(coaches);

        Mentor firstMentor = new Mentor();
        firstMentor.setName("FirstMentor");
        firstMentor.setAge(23);
        Mentor secondMentor = new Mentor();
        secondMentor.setName("SecondMentor");
        secondMentor.setAge(25);
        Mentor thirdMentor = new Mentor();
        thirdMentor.setName("FirstMentor");
        thirdMentor.setAge(18);
        mentorDao.save(firstMentor);
        mentorDao.save(secondMentor);
        mentorDao.save(thirdMentor);
        List<Mentor> byAgeGreaterThan = mentorDao.findByAgeGreaterThan(23);
        System.out.println(byAgeGreaterThan);

        Machine machine = new Machine();
        int currentYear = LocalDate.now().getYear();
        machine.setYear(currentYear - 2);
        Car car = new Car();
        car.setYear(currentYear - 3);
        Truck truck = new Truck();
        truck.setYear(currentYear - 4);
        machineDao.save(car);
        machineDao.save(truck);
        machineDao.save(machine);

        List<Machine> byAgeOlderThan = machineDao.findByAgeOlderThan(2);
        System.out.println(byAgeOlderThan);
        Machine myMachine = new Machine();
        myMachine.setYear(2019);
        machineDao.save(myMachine);
        System.out.println(machineDao.findByAgeOlderThan(2));
    }
}
