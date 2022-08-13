package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Person;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person person) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(person);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save person: " + person, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return person;
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Coach> criteriaQuery = criteriaBuilder.createQuery(Coach.class);
            Root<Coach> root = criteriaQuery.from(Coach.class);
            criteriaQuery.where(criteriaBuilder.greaterThan(root.get("experience"), years));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
