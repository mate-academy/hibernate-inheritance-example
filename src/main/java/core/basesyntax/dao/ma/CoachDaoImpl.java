package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Coach> result = session.createQuery(
                            "FROM Coach WHERE experience > :experience", Coach.class)
                    .setParameter("experience", years)
                    .getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't find coaches", e);
        }
    }
}
