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
        } catch (RuntimeException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal to DB. Animal: " + animal);
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
            Query<Animal> getAnimalByFirstLetter = session.createQuery("from Animal "
                            + "WHERE name LIKE :chars1 OR name LIKE :chars2", Animal.class)
                    .setParameter("chars1", String.valueOf(character).toUpperCase() + "%")
                    .setParameter("chars2", String.valueOf(character).toLowerCase() + "%");
            return getAnimalByFirstLetter.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get list of animals", e);
        }
    }
}
