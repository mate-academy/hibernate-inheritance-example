package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> query = builder.createQuery(Mentor.class);
            Root<Mentor> root = query.from(Mentor.class);
            Predicate greatThanAge = builder.greaterThan(root.get("age"), age);
            query.select(root).where(greatThanAge);
            return session.createQuery(query).getResultList();
        } catch (Exception exception) {
            throw new RuntimeException("Can't get list of mentors with age greater than "
                    + age, exception);
        }
    }
}
