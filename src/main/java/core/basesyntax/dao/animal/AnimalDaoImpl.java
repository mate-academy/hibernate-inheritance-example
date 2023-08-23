package core.basesyntax.dao.animal;

import java.util.List;
import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot add animal: " + animal, e);
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
            return session.createQuery(
                    "FROM Animal "
                        + "WHERE LOWER(name) LIKE LOWER(:ch)", Animal.class)
                    .setParameter("ch", character + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Cannot get animals by character: " + character);
        }
    }
}
