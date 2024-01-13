package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        Transaction transaction = null;
        Session session = null;
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
            throw new DataProcessingException("Cannot add to db animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> animalQuery = session.createQuery("FROM Animal a "
                    + "WHERE SUBSTRING(a.name, 1, 1) = :ch OR SUBSTRING(a.name, 1, 1) = :ch1",
                    Animal.class);
            animalQuery.setParameter("ch", String.valueOf(character).toLowerCase());
            animalQuery.setParameter("ch1", String.valueOf(character).toUpperCase());
            return animalQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Cannot get any animal by first character: "
                    + character, e);
        }
    }
}
