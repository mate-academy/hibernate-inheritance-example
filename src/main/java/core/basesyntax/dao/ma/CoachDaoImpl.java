package core.basesyntax.dao.ma;

import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Person;
import jakarta.persistence.Query;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery("FROM Person p "
                    + "WHERE p.experience > :years", Person.class);
            query.setParameter("years", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get experiance years for coach " + years, e);
        }
    }
}
