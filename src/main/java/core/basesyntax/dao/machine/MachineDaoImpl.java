package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory);
    }

    @Override
    public Machine save(Machine machine) {
        EntityManager entityManager = null;
        EntityTransaction entityTransaction = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(machine);
            entityTransaction.commit();
            return machine;
        } catch (Exception e) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            throw new RuntimeException("Can't add machine to db " + machine, e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        String query = "FROM Machine m WHERE m.year < :age";
        EntityManager entityManager = null;
        try {
            entityManager = entityManagerFactory.createEntityManager();
            return entityManager.createQuery(query, Machine.class)
                    .setParameter("age", LocalDateTime.now().getYear() - age)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get machines older then " + age, e);
        } finally {
            if (entityManager != null) {
                entityManager.close();
            }
        }
    }
}
