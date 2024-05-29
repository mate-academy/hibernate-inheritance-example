package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save animal: "
                    + animal + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Animal> queryForFindByName = cb.createQuery(Animal.class);
            Root<Animal> animalRoot = queryForFindByName.from(Animal.class);
            Predicate namePredicate =
                    cb.or(cb.like(cb.lower(animalRoot.get("name")),
                                    character.toString().toLowerCase() + "%"),
                            cb.like(cb.upper(animalRoot.get("name")),
                                    character.toString().toUpperCase() + "%"));
            queryForFindByName.where(namePredicate);
            return session.createQuery(queryForFindByName).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find animal by name with first letter: "
                    + character, e);
        }
    }
}
