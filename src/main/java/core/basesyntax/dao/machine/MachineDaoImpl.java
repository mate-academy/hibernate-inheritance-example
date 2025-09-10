package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        return (Machine) super.save(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        if (age < 0) {
            throw new IllegalArgumentException("Age must be greater than zero");
        }
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query =
                    session.createQuery("from Machine m "
                                    + "where ((YEAR (CURRENT_DATE)) - m.year) > :age",
                            Machine.class);
            query.setParameter("age", age);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
