package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.DaoOperation;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    private final DaoOperation<Machine> machineDaoOperation =
            new DaoOperation<>();

    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        return machineDaoOperation.add(machine, sessionFactory);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> machineOlderThan =
                    session.createQuery("FROM Machine m "
                            + "WHERE year(current date ) - m.year > :age ",
                    Machine.class);
            machineOlderThan.setParameter("age", age);
            return machineOlderThan.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Could not get machine older than " + age + " years", e);
        }
    }
}
