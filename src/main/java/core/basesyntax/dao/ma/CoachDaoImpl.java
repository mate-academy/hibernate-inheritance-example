package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.time.LocalDate;
import java.util.List;
import core.basesyntax.model.ma.Person;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.zoo.Animal;
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
            Query<Coach> query = session.createQuery("FROM Person WHERE experience > :experience ", Coach.class);
            query.setParameter("experience", years);
            return query.getResultList();
        }
    }
}
