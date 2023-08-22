package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    private static final String AGE_PARAMETER = "age";

    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> getMachineByAgeQuery =
                    session.createQuery("FROM Mentor m "
                            + "WHERE m.age > :" + AGE_PARAMETER, Mentor.class);
            getMachineByAgeQuery.setParameter(AGE_PARAMETER, age);
            return getMachineByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find a mentor by age older than: " + age, e);
        }
    }
}
