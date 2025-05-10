package core.basesyntax.dao.animal;

import core.basesyntax.DataProcessingException;
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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save animal to DB: "
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
            Query<Animal> findByFirstLetterQuery = session.createQuery(
                    "FROM Animal a "
                                + "WHERE a.name LIKE :format1 "
                                + "OR a.name LIKE :format2", Animal.class);
            findByFirstLetterQuery.setParameter("format1",
                    character.toString().toLowerCase() + "%");
            findByFirstLetterQuery.setParameter("format2",
                    character.toString().toUpperCase() + "%");
            return findByFirstLetterQuery.list();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find Animal by first letter of a name: "
            + character, e);
        }
    }
}
