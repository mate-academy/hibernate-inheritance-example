package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Mentor;
import jakarta.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Mentor> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Mentor.class);
            criteriaQuery.from(Mentor.class);
            return session.createQuery(criteriaQuery).getResultList().stream()
                    .filter(c -> c.getAge() > age)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Can't find all mentors with age more then: "
                    + age + " years", e);
        }
    }
}
