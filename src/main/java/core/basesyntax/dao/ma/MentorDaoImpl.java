package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Mentor save(Mentor mentor) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(mentor);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("can't save mentor to DB:" + mentor, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return mentor;
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Mentor> query = session.createQuery(
                    "FROM Mentor WHERE age > :age ", Mentor.class);
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentors by age:" + age);
        }
    }
}
