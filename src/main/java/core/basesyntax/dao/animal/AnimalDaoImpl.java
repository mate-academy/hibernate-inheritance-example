package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
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
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't add animal: "
                    + animal + " to the DB. ", e);
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
            character = Character.toLowerCase(character);
            Query<Animal> findAllAnimalQuery = session.createQuery("FROM Animal a"
                    + " WHERE LOWER(a.name) LIKE :character ", Animal.class);
            findAllAnimalQuery.setParameter("character", character + "%");
            return findAllAnimalQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Couldn't get all animals by first letter name: "
                    + character + " from DB. ", e);
        }
    }
}
