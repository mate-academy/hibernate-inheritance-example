import core.basesyntax.dao.ma.*;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import core.basesyntax.util.HibernateUtil;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PersonDao personDao = new PersonDaoImpl(HibernateUtil.getSessionFactory());

        Person person = new Person();
        person.setAge(150);
        person.setName("bob");
        personDao.save(person);

        Mentor mentor = new Mentor();
        mentor.setAge(234);
        mentor.setName("Mykola");
        personDao.save(mentor);

        Coach coach = new Coach();
        coach.setAge(6);
        coach.setName("Max");
        coach.setExperience(5);
        coach.setTrack(Coach.Track.JAVA);
        personDao.save(coach);

//        MentorDao mentorDao = new MentorDaoImpl(HibernateUtil.getSessionFactory());
//        List<Mentor> byAgeGreaterThan = mentorDao.findByAgeGreaterThan(250);
//        for (Mentor mentor1: byAgeGreaterThan) {
//            System.out.println(mentor1);
//        }

        CoachDao coachDao = new CoachDaoImpl(HibernateUtil.getSessionFactory());
        List<Coach> byExperienceGreaterThan = coachDao.findByExperienceGreaterThan(1);
        for(Coach coach1: byExperienceGreaterThan) {
            System.out.println(coach1);
        }


    }
}
