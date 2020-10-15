package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.exceptions.DataProcessingException;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;


public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> query = session.createQuery("FROM Mentor m "
                    + "WHERE m.age > :age ", Mentor.class);
            query.setParameter("age", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Failed to find Mentors older than "
                    + years + " years", e);
        }
    }
}
