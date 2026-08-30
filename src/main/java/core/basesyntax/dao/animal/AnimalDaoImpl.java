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
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        char paramLower = Character.toLowerCase(character);
        char paramUpper = Character.toUpperCase(character);

        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("FROM Animal a "
                            + "WHERE a.name LIKE :prmLower "
                            + "OR a.name LIKE :prmUpper", Animal.class)
                    .setParameter("prmLower", paramLower + "%")
                    .setParameter("prmUpper", paramUpper + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find by name first letter: " + character, e);
        }
    }
}
