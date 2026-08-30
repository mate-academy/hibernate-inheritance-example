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
            animal.setName(
                    Character.toUpperCase(animal.getName().charAt(0))
                            + animal.getName().substring(1));
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't inject " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String query = """
                FROM Animal animal
                WHERE animal.name LIKE :value
                """;
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(query, Animal.class)
                    .setParameter("value", character + "%")
                    .list();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't get all animals by the  first character of name " + character, e);
        }
    }
}
