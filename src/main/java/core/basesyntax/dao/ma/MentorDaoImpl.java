package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> cq = cb.createQuery(Mentor.class);
            Root<Mentor> root = cq.from(Mentor.class);
            cq.where(cb.gt(root.get("age"), age));

            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get mentors older than age: " + age, e);
        }
    }
}
