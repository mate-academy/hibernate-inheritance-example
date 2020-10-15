package core.basesyntax.dao.ma;

import core.basesyntax.library.Dao;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Mentor;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Dao
public class MentorDaoImpl extends PersonDaoImpl implements MentorDao {
    public MentorDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Mentor> findByAgeGreaterThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Mentor> orderCriteriaQuery =
                    criteriaBuilder.createQuery(Mentor.class);
            Root<Mentor> orderRoot =
                    orderCriteriaQuery.from(Mentor.class);
            Predicate predicate =
                    criteriaBuilder.gt(orderRoot.get("age"), age);
            orderCriteriaQuery.select(orderRoot).where(predicate);
            return session.createQuery(orderCriteriaQuery.distinct(true)).getResultList();
        }
    }
}
