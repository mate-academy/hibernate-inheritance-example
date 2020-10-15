package core.basesyntax.dao.machine;

import core.basesyntax.dao.GeneralDaoImpl;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends GeneralDaoImpl<Machine> implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Machine m "
                            + "WHERE :year - m.year > :age", Machine.class)
                    .setParameter("year", LocalDate.now().getYear())
                    .setParameter("age", age)
                    .getResultList();
        }
    }
}
