package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            List<Coach> coaches = session.createQuery(
                    "from Coach where experience > :years", Coach.class)
                    .setParameter("years", years)
                    .getResultList();
            transaction.commit();
            return coaches;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    "Can`t find coaches with experience greater than: " + years, e);
        }
    }
}
