package core.basesyntax.dao.ma;

import core.basesyntax.model.ma.Coach;
import core.basesyntax.model.ma.Person;
import java.util.ArrayList;
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
            Query<Person> query = session.createQuery(
                    "FROM Person p WHERE p.experience > :years", Person.class);
            query.setParameter("years", years);
            List<Coach> coachList = new ArrayList<>();
            for (Person person : query.getResultList()) {
                if (person instanceof Coach) {
                    coachList.add((Coach) person);
                }
            }
            return coachList;
        } catch (Exception e) {
            throw new RuntimeException("Can't get coach with experience greater than: "
                    + years, e);
        }
    }
}
