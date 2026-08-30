package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        int realYear = 2025 - age;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Mentor m where m.year < :year", Mentor.class)
                    .setParameter("year", realYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find user older than " + age + " years old", e);
        }
    }
}
