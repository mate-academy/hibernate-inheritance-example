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
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> query = criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> mentorRoot = query.from(Mentor.class);
            query.where(criteriaBuilder.gt(mentorRoot.get("age"), age));
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new RuntimeException(String.format("Can`t find mentor "
                    + "with age more than %d", age), e);
        }
    }
}
