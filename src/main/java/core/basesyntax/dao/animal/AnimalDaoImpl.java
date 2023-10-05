package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import jakarta.persistence.Query;
import java.util.List;
import java.util.Objects;
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
            Objects.requireNonNull(transaction).rollback();
            throw new RuntimeException("Can't add animal to db", e);
        } finally {
            Objects.requireNonNull(session).close();
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query query = session.createQuery(
                    "from Animal WHERE name LIKE :fl1 OR name LIKE :fl2", Animal.class)
                    .setParameter("fl1", character.toString().toUpperCase() + "%")
                    .setParameter("fl2", character.toString().toLowerCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find an animal with first letter: " + character, e);
        }
    }
}
