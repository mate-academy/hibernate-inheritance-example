package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert to DB animal: "
                    + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            String uppercaseCharacter = Character.toUpperCase(character) + "%";
            String lowercaseCharacter = Character.toLowerCase(character) + "%";
            String hqlQuery = "FROM Animal a "
                    + "WHERE a.name LIKE :uppercaseCharacter "
                    + "OR a.name LIKE :lowercaseCharacter";
            Query<Animal> query = session.createQuery(hqlQuery, Animal.class);
            query.setParameter("uppercaseCharacter", uppercaseCharacter);
            query.setParameter("lowercaseCharacter", lowercaseCharacter);

            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find animal by first"
                    + " name letter: " + character);
        }
    }
}
