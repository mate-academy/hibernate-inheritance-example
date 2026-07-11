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
        return (Machine) sessionFactory.getCurrentSession()
                .save(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        return sessionFactory.openSession()
                .createQuery("FROM CAR WHERE age > :age", Machine.class)
                .setParameter("age", age)
                .getResultList();
    }
}
