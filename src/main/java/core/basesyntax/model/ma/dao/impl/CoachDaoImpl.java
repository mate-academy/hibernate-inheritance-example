package core.basesyntax.model.ma.dao.impl;

import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.dao.CoachDao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class CoachDaoImpl implements CoachDao {
    @Override
    public List<Coach> getWithExperienceMoreThan(Integer years) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Coach> query = session.createQuery(
                    "from Coach c where c.experience > :experience");
            query.setParameter("experience", years);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find Machines which are older then  - "
                    + years + " years", e);
        }
    }
}
