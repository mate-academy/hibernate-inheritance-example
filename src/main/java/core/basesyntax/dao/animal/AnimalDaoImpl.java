package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    private static final String LETTER_LOWERCASE_PARAMETER = "letter_lowercase";
    private static final String LETTER_UPPERCASE_PARAMETER = "letter_uppercase";
    private static final String PERCENTAGE_STR = "%";

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
            throw new DataProcessingException("Can't save animal to DB. Animal: " + animal, e);
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
            return session.createQuery("from Animal a "
                            + "where a.name like :letter_lowercase "
                            + "or a.name like :letter_uppercase", Animal.class)
                    .setParameter(LETTER_LOWERCASE_PARAMETER,
                            (character + PERCENTAGE_STR).toLowerCase())
                    .setParameter(LETTER_UPPERCASE_PARAMETER,
                            (character + PERCENTAGE_STR).toUpperCase())
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find animal by first character. "
                    + "Character: " + character, e);
        }
    }
}
