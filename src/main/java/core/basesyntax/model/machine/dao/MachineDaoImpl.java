package core.basesyntax.model.machine.dao;

import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MachineDaoImpl implements MachineDao {
    @Override
    public Machine save(Machine machine) {
        Session session = null;
        Transaction transaction = null;
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
            throw new RuntimeException("There was an error inserting machine "
                    + machine, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Machine> criteriaQuery
                    = criteriaBuilder.createQuery(Machine.class);
            Root<Machine> root = criteriaQuery.from(Machine.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.greaterThan(root.get("year"), age)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving machines", e);
        }
    }
}
