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
        Session session = null;
        Transaction transaction = null;
        try {
            session = openSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            transactionNullCheck(transaction);
            throw new RuntimeException("Can't persist animal: " + animal, e);
        } finally {
            closeSession(session);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = openSession()) {
            return session.createQuery("from Animal "
                            + "where lower(name) like :letter",
                            Animal.class)
                    .setParameter("letter", letterMap(character))
                    .list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animals by names first letter: "
                    + letterMap(character), e);
        }
    }

    private String letterMap(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character can't be null");
        }
        return Character.toLowerCase(character) + "%";
    }
}
