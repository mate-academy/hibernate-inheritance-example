package core.basesyntax.dao.ma;

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
            CriteriaQuery<Mentor> cq = cb.createQuery(Mentor.class);
            Root<Mentor> rootMentor = cq.from(Mentor.class);

            Predicate getMentorByAgeGreaterThan = cb.greaterThan(rootMentor.get("age"), age);

            cq.select(rootMentor).where(getMentorByAgeGreaterThan);
            return session.createQuery(cq).getResultList();
        }
    }
}
