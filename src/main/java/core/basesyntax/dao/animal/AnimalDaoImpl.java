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
            throw new RuntimeException("Can't save animal: " + animal);
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
            Query<Animal> animalQuery = session.createQuery("FROM Animal a "
                            + "WHERE a.name LIKE : upperCharacter "
                            + "OR a.name LIKE : lowerCharacter", Animal.class);
            animalQuery.setParameter("upperCharacter", character.toString().toUpperCase() + "%");
            animalQuery.setParameter("lowerCharacter", character.toString().toLowerCase() + "%");
            return animalQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by first character: " + character);
        }
    }
}
