package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Person;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        try (Session session = this.sessionFactory.openSession()) {
            Query query = session.createQuery("select p FROM person p "
                    + "where p.experience >:years", Person.class);
            query.setParameter("years", years);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find coach with experience +" + years, e);
        }
    }
}
