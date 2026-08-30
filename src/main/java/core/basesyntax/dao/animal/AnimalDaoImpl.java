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
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(
                    "Can't save animal entity: " + animal + " , error: ", e);
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            String pattern = character.toString().toLowerCase() + "%";
            return session
                    .createQuery("FROM Animal a WHERE LOWER(a.name) LIKE :pattern", Animal.class)
                    .setParameter("pattern", pattern)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error occurred while finding Animal that names begins with: "
                            + character + " , error: ", e);
        }

    }
}
