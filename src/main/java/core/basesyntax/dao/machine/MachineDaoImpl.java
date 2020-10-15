package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    protected MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        return null;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int maxYear = LocalDate.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Machine m where m.year <= :year", Machine.class)
                    .setParameter("year", maxYear)
                    .getResultList();
        }
    }
}
