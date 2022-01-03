package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
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
            Root<Mentor> machineRoot = query.from(Mentor.class);
            Predicate ageGT = cb.gt(machineRoot.get("age"), age);
            query.where(ageGT);
            return session.createQuery(query).getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find mentor older than: " + age, e);
        }
    }
}
