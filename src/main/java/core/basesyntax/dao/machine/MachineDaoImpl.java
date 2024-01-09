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
            session = factory.openSession();
            transaction = session.beginTransaction();
            session.persist(machine);
            transaction.commit();
            return machine;
        } catch (Exception e) {
            if(transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cant insert machine " + machine, e);
        } finally {
            if(session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        LocalDate localDate = LocalDate.now();
        int year = localDate.getYear() - age;
        try (Session session = factory.openSession()) {
            Query<Machine> query = session.createQuery("FROM Machine m "
                    + "WHERE year < :year", Machine.class);
            query.setParameter("year", year);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cant fiend machine where age greater than " + age, e);
        }
    }
}
