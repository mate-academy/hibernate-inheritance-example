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
            session.persist(animal);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal: " + animal, ex);
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
            Query<Animal> animalQuery = session.createQuery("FROM Animal an "
                    + "WHERE UPPER(an.name) LIKE CONCAT(:character, '%')",
                    Animal.class);
            animalQuery.setParameter("character", character);
            return animalQuery.getResultList();
        } catch (Exception ex) {
            throw new RuntimeException("Can't find animals by first letter: " + character, ex);
        }
    }
}
