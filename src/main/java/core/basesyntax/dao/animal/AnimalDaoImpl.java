package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal: " + animal, e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Animal> cq = cb.createQuery(Animal.class);
            Root<Animal> root = cq.from(Animal.class);
            Predicate firstLetterPredicate = cb.or(
                    cb.like(root.get("name"), Character.toLowerCase(character) + "%"),
                    cb.like(root.get("name"), Character.toUpperCase(character) + "%")
            );
            cq.where(firstLetterPredicate);
            return session.createQuery(cq).getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by name first letter: " + character, e);
        }
    }
}
