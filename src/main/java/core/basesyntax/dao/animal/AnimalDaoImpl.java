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
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't add animal: " + animal + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            String c = "" + character;
            return session.createQuery("FROM Animal a "
                            + "WHERE a.name LIKE :smallChar "
                            + "OR a.name LIKE :bigChar ", Animal.class)
                    .setParameter("smallChar", c.toLowerCase() + "%")
                    .setParameter("bigChar", c.toUpperCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't find animals by first letter starts with character " + character, e);
        }
    }
}
