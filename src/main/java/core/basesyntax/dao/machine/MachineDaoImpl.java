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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save machine: " + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        int yearOfMachine = LocalDate.now().getYear() - age;
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> sessionQuery
                    = session.createQuery("select m from Machine m "
                    + "where m.year < :age", Machine.class);
            sessionQuery.setParameter("age", yearOfMachine);
            return sessionQuery.getResultList();
        }
    }
}
