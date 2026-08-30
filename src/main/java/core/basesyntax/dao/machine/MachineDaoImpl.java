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
        if (machine == null) {
            throw new IllegalArgumentException("Machine cannot be null");
        }
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            session.persist(machine);
            session.flush();
            tx.commit();
            return machine;
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age cannot be negative");
        }
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            // Age of a machine is calculated as (currentYear - m.year)
            // We need machines with age strictly greater than the given age
            var query = session.createQuery(
                    "FROM Machine m WHERE (YEAR(CURRENT_DATE()) - m.year) > :age",
                    Machine.class
            );
            query.setParameter("age", age);
            List<Machine> result = query.getResultList();
            tx.commit();
            return result;
        }
    }
}
