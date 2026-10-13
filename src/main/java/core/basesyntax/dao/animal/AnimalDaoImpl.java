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
            throw new DataProcessingException("Can't insert animal " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        Session session = null;
        String searchPattern = character + "%";
        try {
            session = sessionFactory.openSession();
            return session.createQuery(
                            "from Animal"
                                    + " WHERE name like :firstCharacterUpper "
                                    + "OR name like :firstCharacterLower",
                            Animal.class)
                    .setParameter("firstCharacterUpper", searchPattern.toUpperCase())
                    .setParameter("firstCharacterLower", searchPattern.toLowerCase())
                    .list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any "
                    + Animal.class.getSimpleName(), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
