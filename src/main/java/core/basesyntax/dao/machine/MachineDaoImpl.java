package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao<Machine> implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            int currentYear = LocalDate.now().getYear();
            int appropriateYear = currentYear - age;
            Query<Machine> query = session.createQuery(
                    "from Machine m where m.year < :appropriateYear", Machine.class);
            query.setParameter("appropriateYear", appropriateYear);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t get machines older that age " + age, e);
        }
    }
}
