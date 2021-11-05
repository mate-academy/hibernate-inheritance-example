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
        String query = "FROM Mentor m WHERE age > :age";
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> queryFindByAgeGreaterThan = session.createQuery(query);
            queryFindByAgeGreaterThan.setParameter("age", age).getResultList();
            return queryFindByAgeGreaterThan.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't get mentor by age" + age, e);
        }
    }
}
