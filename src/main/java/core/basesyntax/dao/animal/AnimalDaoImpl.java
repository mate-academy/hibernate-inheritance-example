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
            throw new RuntimeException("Can't save an animal to DB " + animal, e);
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
            Query<Animal> getByNameFirstLetterQuery = session.createQuery("FROM Animal a "
                    + "WHERE a.name LIKE :lower_character "
                    + "OR a.name LIKE :upper_character");
            getByNameFirstLetterQuery.setParameter(
                    "lower_character", Character.toLowerCase(character) + "%");
            getByNameFirstLetterQuery.setParameter(
                    "upper_character", Character.toUpperCase(character) + "%");
            return getByNameFirstLetterQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animals by 1st letter " + character, e);
        }
    }
}
