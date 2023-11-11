package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(machine);
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Machine WHERE year < :age";
        return session.createQuery(hql, Machine.class)
                .setParameter("age", age)
                .getResultList();
    }
}
