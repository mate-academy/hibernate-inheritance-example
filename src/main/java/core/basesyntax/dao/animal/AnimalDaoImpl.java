package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        var session = sessionFactory.openSession();
        var transaction = session.beginTransaction();
        try {
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Error while saving animal: " + animal, e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (var session = sessionFactory.openSession()) {
            var criteriaBuilder = session.getCriteriaBuilder();
            var criteriaQuery = criteriaBuilder.createQuery(Animal.class);
            var root = criteriaQuery.from(Animal.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.like(root.get("name"), character + "%"));

            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
