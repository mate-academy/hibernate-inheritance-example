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
    public Machine save(Machine machine) {
        return super.save(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Machine "
                    + "where year < :year", Machine.class)
                    .setParameter("year", LocalDate.now().getYear() - age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get machines older than " + age, e);
        }
    }
}
