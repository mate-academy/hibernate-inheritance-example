package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query<Coach> findByNameQuery
                    = session.createQuery("from Coach c WHERE c.experience > :age",Coach.class);
            findByNameQuery.setParameter("age",years);
            return findByNameQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can`t find coach with experience greater than: "
                    + years,e);
        }
    }
}
