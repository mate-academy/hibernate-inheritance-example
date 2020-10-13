package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcesingException;
import core.basesyntax.model.ma.Mentor;
import java.util.List;

import core.basesyntax.model.zoo.Animal;
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
            Query<Mentor> query = session.createQuery("FROM Mentor " +
                    "WHERE age > :param",Mentor.class);
            query.setParameter("param", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcesingException("Can't get Mentors with age > " + age, e);
        }
    }
}
