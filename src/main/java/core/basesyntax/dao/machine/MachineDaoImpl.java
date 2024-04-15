package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

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
        int year = LocalDate.now().getYear();
        int maxYear = year - age;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Machine where year < :maxYear", Machine.class)
                    .setParameter("maxYear", maxYear)
                    .getResultList();
        }
    }
}
