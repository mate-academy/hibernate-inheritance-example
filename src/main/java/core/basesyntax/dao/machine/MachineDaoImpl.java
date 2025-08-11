package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
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
        return addEntity(machine);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {

        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine m "
                    + "WHERE m.year < :age", Machine.class);
            query.setParameter("age", LocalDate.now().getYear() - age);
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find machines older than " + age, e);
        }
    }
}
