package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.EntityManager;
import java.util.List;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            return entityManager.createQuery("from Mentor "
                                    + "WHERE age > :age",
                            Mentor.class)
                    .setParameter("age", age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor by age " + age, e);
        }
    }
}
