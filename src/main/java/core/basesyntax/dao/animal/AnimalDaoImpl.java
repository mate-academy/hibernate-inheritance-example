package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        EntityTransaction entityTransaction = null;

        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(animal);
            entityTransaction.commit();

            return animal;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw new RuntimeException("Can't save animal " + animal, e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            String pattern = Character.toLowerCase(character) + "%";

            return entityManager.createQuery("from Animal "
                                    + "WHERE lower(name) "
                                    + "LIKE :pattern",
                            Animal.class)
                    .setParameter("pattern", pattern)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by first letter " + character, e);
        }
    }
}
