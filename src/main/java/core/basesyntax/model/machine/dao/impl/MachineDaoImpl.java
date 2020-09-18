package core.basesyntax.model.machine.dao.impl;

import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.machine.dao.MachineDao;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MachineDaoImpl implements MachineDao {
    @Override
    public Machine save(Machine machine) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot insert machine entity - " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> getOlderThan(Integer years) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            int minYear = LocalDate.now().getYear() - years;
            Query<Machine> query = session.createQuery(
                    "from Machine m where m.year < :years");
            query.setParameter("years", minYear);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find Machines which are older then  - "
                    + years + " years", e);
        }
    }
}
