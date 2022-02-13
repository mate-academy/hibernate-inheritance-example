package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.util.HibernateUtil;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Mentor> query = session.createQuery("FROM Mentor m " + "WHERE m.age > "
                                                              + ":age",
                    Mentor.class);
            query.setParameter("age", age);
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find  by year: " + age, e);
        }
    }
}
