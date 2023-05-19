package core.basesyntax.dao.ma;

import core.basesyntax.exceptin.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM mentor_person m WHERE age > :age")
                    .setParameter("age", age).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("can`t find mentor with age more " + age, e);
        }
    }
}
