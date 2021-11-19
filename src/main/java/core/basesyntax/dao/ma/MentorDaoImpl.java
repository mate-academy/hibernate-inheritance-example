package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.Person;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("select p FROM person p "
                    + "where p.age >:age", Person.class);
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach older than +" + age + " years", e);
        }
    }
}
