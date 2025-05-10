package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> query = cb.createQuery(Mentor.class);
            Root<Mentor> root = query.from(Mentor.class);
            query.select(root).where(cb.greaterThan(root.get("age"), age));
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get the mentor by age: " + age, e);
        }
    }
}
