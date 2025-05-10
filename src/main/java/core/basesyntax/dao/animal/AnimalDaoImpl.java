package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
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
            throw new RuntimeException("Can't insert an animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String characterLowerCase = String.valueOf(character).toLowerCase();
        String characterUpperCase = String.valueOf(character).toUpperCase();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Animal a "
                    + "WHERE SUBSTRING(name, 1, 1) = :characterLowerCase OR "
                            + "SUBSTRING(name, 1, 1) = :characterUpperCase", Animal.class)
                            .setParameter("characterLowerCase", characterLowerCase)
                            .setParameter("characterUpperCase", characterUpperCase)
                            .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find an animal by first letter: " + character, e);
        }
    }
}
