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
        Session session = null;
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
            throw new RuntimeException("Can't save in data base this machine: "
                    + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        String hql = "SELECT m FROM Machine m "
                + "WHERE m.year < :year";
        try (Session session = sessionFactory.openSession()) {
            Query<Machine> query = session.createQuery(hql, Machine.class);
            int year = LocalDate.now().getYear() - age;
            query.setParameter("year", year);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find  machines by this age: '"
                    + age, e);
        }
    }
}
