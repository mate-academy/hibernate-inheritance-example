package core.basesyntax;

import core.basesyntax.dao.ma.CoachDao;
import core.basesyntax.dao.ma.CoachDaoImpl;
import core.basesyntax.model.ma.Coach;
import org.hibernate.SessionFactory;

public class Main {
    private static final SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public static void main(String[] args) {
        // Testing @Enumerated annotation
        Coach coach = new Coach();
        coach.setName("Bohdan");
        coach.setAge(25);
        coach.setTrack(Coach.Track.JAVA);

        CoachDao coachDao = new CoachDaoImpl(sessionFactory);
        coachDao.save(coach);
    }
}
