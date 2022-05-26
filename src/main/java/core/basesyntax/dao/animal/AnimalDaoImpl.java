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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save the animal " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> findByFirstLetterQuery = session.createQuery("FROM Animal a "
                    + "WHERE a.name LIKE :upperCase OR a.name LIKE :lowerCase", Animal.class);
            findByFirstLetterQuery.setParameter("upperCase",
                    Character.toUpperCase(character) + "%");
            findByFirstLetterQuery.setParameter("lowerCase",
                    Character.toLowerCase(character) + "%");
            return findByFirstLetterQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find by first letter " + character, e);
        }
    }
}
