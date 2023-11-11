package core.basesyntax.dao.ma;

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
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            String hql = "FROM Mentor WHERE age > :age";
            List<Mentor> mentors = session.createQuery(hql, Mentor.class)
                    .setParameter("age", age)
                    .getResultList();
            transaction.commit();
            return mentors;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw e;
        } finally {
            session.close();
        }
    }
}
