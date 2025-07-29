package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            throw new RuntimeException("Can`t save machine object!: " + machine, e);
        }
    }

    @Override
        public List<Machine> findByAgeOlderThan(int year) {
        int yearThroesHold = LocalDate.now().getYear() - year;
        try (var session = sessionFactory.openSession()) {
            var query = session.createQuery(
                    "FROM Machine a WHERE a.year < :year", Machine.class);
            query.setParameter("year", yearThroesHold);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t find machines older than: " + year + "!", e);
        }
    }
}
