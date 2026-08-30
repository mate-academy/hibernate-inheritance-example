import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.dao.ma.MentorDao;
import core.basesyntax.dao.ma.MentorDaoImpl;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.SessionFactory;

public class PersonRunner {
    public static void main(String[] args) {
        List<Runnable> runnableList = new ArrayList<>();
        runnableList.add(getRunnable());
        runnableList.forEach(Runnable::run);
    }

    public static Runnable getRunnable() {
        return () -> {
            SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

            Coach tom = new Coach();
            tom.setAge(3);
            tom.setExperience(10);
            tom.setName("Tomas");
            tom.setTrack(Coach.Track.JAVA);
            CoachDao coachDao = new CoachDaoImpl(sessionFactory);
            coachDao.save(tom);

            Mentor bob = new Mentor();
            bob.setAge(35);
            bob.setName("Bob");
            MentorDao mentorDao = new MentorDaoImpl(sessionFactory);
            mentorDao.save(bob);

            List<Coach> coachList = coachDao.findByExperienceGreaterThan(5);
            System.out.println("coaches in list: " + coachList.size());
            coachList.forEach(System.out::println);

            List<Mentor> mentorsList = mentorDao.findByAgeGreaterThan(27);
            System.out.println("mentors in list: " + mentorsList.size());
            mentorsList.forEach(System.out::println);
        };
    }

    ;
}
