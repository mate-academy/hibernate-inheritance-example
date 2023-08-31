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
    private static final String NAME_FIRST_LETTER_PARAM = "firstLetter";

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
            throw new DataProcessingException("Can't insert an animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String hql = "FROM Animal a "
                + "WHERE UPPER(a.name) "
                + "LIKE :" + NAME_FIRST_LETTER_PARAM;
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> getAnimalsByNameFirstLetter = session.createQuery(hql, Animal.class);
            getAnimalsByNameFirstLetter.setParameter(NAME_FIRST_LETTER_PARAM, character + "%");
            return getAnimalsByNameFirstLetter.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't get any animal by name first letter: "
                                                + character, e);
        }
    }
}
