package core.basesyntax.dao.ma;

import core.basesyntax.library.Dao;
import core.basesyntax.model.ma.Coach;
import java.util.List;

import core.basesyntax.model.machine.Machine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Dao
public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> orderCriteriaQuery =
                    criteriaBuilder.createQuery(Coach.class);
            Root<Coach> orderRoot =
                    orderCriteriaQuery.from(Coach.class);
            Predicate predicate =
                    criteriaBuilder.gt(orderRoot.get("experience"), years);
            orderCriteriaQuery.select(orderRoot).where(predicate);
            return session.createQuery(orderCriteriaQuery.distinct(true)).getResultList();
        }
    }
}
