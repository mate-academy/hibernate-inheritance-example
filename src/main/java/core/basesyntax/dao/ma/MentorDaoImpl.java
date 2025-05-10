package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Mentor> mentors = session.createQuery("from Mentor where age > :age", Mentor.class)
                    .setParameter("age", age)
                    .getResultList();
            transaction.commit();
            return mentors;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can`t find mentors with age greater than: " + age, e);
        }
    }
}
