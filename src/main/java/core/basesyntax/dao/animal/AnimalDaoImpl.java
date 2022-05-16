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
            throw new DataProcessingException("Can`t add " + animal + " to db",e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession();) {
            return session.createQuery("FROM Animal a "
                            + "WHERE a.name LIKE :uppercase OR a.name LIKE :lowercase",
                            Animal.class)
                    .setParameter("uppercase", Character.toUpperCase(character) + "%")
                    .setParameter("lowercase", Character.toLowerCase(character) + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get all animals by character: " + character, e);
        }
    }
}
