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
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can`t add animal " + animal + " to the DB", e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String query = "FROM Animal a where lower(a.name) like  :character";
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> animalQuery = session.createQuery(query, Animal.class);
            animalQuery.setParameter("character",
                    Character.toLowerCase(character) + "%");
            return animalQuery.getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Can`t get animal by first letter " + character, e);
        }
    }
}
