package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> getMentorList = session.createQuery("select m from Mentor m "
                    + "  where m.age > :age", Mentor.class)
                    .setParameter("age", age);
            return getMentorList.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentors greater than " + age
                    + " age", e);
        }
    }
}
