package core.basesyntax.model.ma.dao.impl;

import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.ma.Mentor;
import core.basesyntax.model.ma.dao.MentorDao;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class MentorDaoImpl implements MentorDao {
    @Override
    public List<Mentor> getOlderThan(int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Mentor> query = session.createQuery(
                    "from Mentor m where m.age > :age");
            query.setParameter("age", age);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find Machines which are older then  - "
                    + age + " years", e);
        }
    }
}
