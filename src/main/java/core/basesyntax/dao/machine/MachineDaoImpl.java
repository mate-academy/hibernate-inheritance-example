package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao<Machine> implements MachineDao {
    protected MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Machine "
                    + "WHERE year > :age", Machine.class)
                    .setParameter("age", LocalDate.now().getYear() - age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find machines older then " + age, e);
        }
    }
}
