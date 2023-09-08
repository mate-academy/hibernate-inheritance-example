package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import core.basesyntax.util.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
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
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> query =
                    criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> mentorRoot = query.from(Mentor.class);
            Predicate ageGt = criteriaBuilder.gt(mentorRoot.get("age"), age);
            query.where(ageGt);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find mentor by age greater than : " + age, e);
        }
    }
}
