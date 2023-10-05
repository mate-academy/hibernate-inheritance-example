package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        try {
            sessionFactory.inTransaction(s -> s.persist(machine));
            return machine;
        } catch (Exception e) {
            throw new RuntimeException("Can't add machine to the DB", e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try {
            return sessionFactory.fromSession(s -> s.createQuery("FROM Machine m "
            + "WHERE (YEAR(CURRENT DATE ) - m.year) > :age", Machine.class)
                    .setParameter("age", age)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("No machine older than " + age, e);
        }
    }
}
