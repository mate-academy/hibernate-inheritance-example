package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            int currYear = LocalDate.now().getYear();
            Query getMachineByAgeOlderThanQuery =
                    session.createQuery("FROM Machine m WHERE :cy - m.year > :age");
            getMachineByAgeOlderThanQuery.setParameter("cy", currYear);
            getMachineByAgeOlderThanQuery.setParameter("age", age);
            return getMachineByAgeOlderThanQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
