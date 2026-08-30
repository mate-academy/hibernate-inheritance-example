package core.basesyntax.dao.machine;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.machine.Machine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;

public class MachineDaoImpl extends AbstractDao implements MachineDao {
    public MachineDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Machine save(Machine machine) {
        EntityTransaction entityTransaction = null;

        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(machine);
            entityTransaction.commit();

            return machine;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw new RuntimeException("Can't save machine " + machine, e);
        }
    }

    @Override
    public List<Machine> findByAgeOlderThan(int age) {
        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            int currentYear = LocalDate.now().getYear();
            int limitYear = currentYear - age;

            return entityManager.createQuery("from Machine "
                                    + "WHERE year < :limitYear",
                            Machine.class)
                    .setParameter("limitYear", limitYear)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find Machine by age " + age, e);
        }
    }
}
