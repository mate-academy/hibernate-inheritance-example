package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Mentor;
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
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> queryForFindMentor = cb.createQuery(Mentor.class);
            Root<Mentor> mentorRoot = queryForFindMentor.from(Mentor.class);
            Predicate mentorAgePredicate = cb.gt(mentorRoot.get("age"), age);
            queryForFindMentor.where(mentorAgePredicate);
            return session.createQuery(queryForFindMentor).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find mentor with age greater than: "
                    + age, e);
        }
    }
}
