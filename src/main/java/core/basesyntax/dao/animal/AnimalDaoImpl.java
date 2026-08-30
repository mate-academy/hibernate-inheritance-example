package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rollbackEx) {
                    throw new DataProcessingException("Rollback failed.", rollbackEx);
                }
            }
            throw new DataProcessingException("Can`t save Animal to DB.", e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character can`t be null");
        }
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "SELECT a FROM Animal a WHERE LOWER(a.name) LIKE LOWER(:letter)", Animal.class)
                    .setParameter("letter", character.toString() + "%")
                    .getResultList();
        }
    }
}
