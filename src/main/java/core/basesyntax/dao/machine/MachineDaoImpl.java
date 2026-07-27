package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public Machine save(Machine machine) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = (Transaction) session.beginTransaction();
        try {
            session.persist(machine);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't save machine", e);
        }
        return machine;
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Machine> result = session.createQuery(
                            "FROM Machine WHERE year < :threshold", Machine.class)
                    .setParameter("threshold", 2026 - age)
                    .getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't find machines", e);
        }
    }
}
