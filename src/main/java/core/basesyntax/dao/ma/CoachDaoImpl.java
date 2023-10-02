package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import java.util.List;
import org.hibernate.SessionFactory;

public class CoachDaoImpl extends PersonDaoImpl implements CoachDao {
    public CoachDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Coach> findByExperienceGreaterThan(int years) {
        return performReturnWithoutTx(session ->
                session.createQuery("from Coach c where c.experience > :years", Coach.class)
                        .setParameter("years", years)
                        .getResultList()
        );
    }
}
