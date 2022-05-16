package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
            Query<Mentor> findByNameQuery
                    = session.createQuery("from Mentor m WHERE m.age > :age",Mentor.class);
            findByNameQuery.setParameter("age",age);
            return findByNameQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find coach with age greater than: " + age,e);
        }
    }
}
