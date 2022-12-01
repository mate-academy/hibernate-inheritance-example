package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
            throw new RuntimeException("Can't insert a machine: " + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaQuery<Machine> criteriaQuery = session.getCriteriaBuilder()
                    .createQuery(Machine.class);
            criteriaQuery.from(Machine.class);
            List<Machine> unSorted = session.createQuery(criteriaQuery).getResultList();
            List<Machine> sorted = new ArrayList<>();
            for (int i = 0; i < unSorted.size(); i++) {
                if (LocalDateTime.now().getYear() - unSorted.get(i).getYear() > age) {
                    sorted.add(unSorted.get(i));
                }
            }
            return sorted;
        }
    }
}
