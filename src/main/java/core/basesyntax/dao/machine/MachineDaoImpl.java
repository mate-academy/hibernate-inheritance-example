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
        return performReturnWithinTx(session -> {
            session.persist(machine);
            return machine;
        });
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        return performReturnWithoutTx(session ->
                session.createQuery("from Machine m "
                                + "where (year(current_date) - m.year) > :age", Machine.class)
                        .setParameter("age", age)
                        .getResultList()
        );
    }
}
