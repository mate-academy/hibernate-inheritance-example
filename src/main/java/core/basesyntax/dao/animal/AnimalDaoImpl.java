package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Failed to save an animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Animal> criteriaQuery =
                    criteriaBuilder.createQuery(Animal.class);
            Root<Animal> root = criteriaQuery.from(Animal.class);

            criteriaQuery.select(root)
                    .where(criteriaBuilder.like(criteriaBuilder.upper(root.get("name")),
                            String.valueOf(character) + '%'));
            return session.createQuery(criteriaQuery).getResultList();
        }
    }
}
