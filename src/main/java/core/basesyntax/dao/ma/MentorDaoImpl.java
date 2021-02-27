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
            Query<Mentor> findByAge = session.createQuery("select m "
                    + "from Mentor m "
                    + "where m.age > :age", Mentor.class);
            findByAge.setParameter("age", age);
            return findByAge.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not find by age", e);
        }
    }
}
