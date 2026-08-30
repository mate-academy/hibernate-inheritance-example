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
        Transaction tx = null;
        try {
            session = sessionFactory.openSession();
            tx = session.beginTransaction();
            session.persist(animal);
            tx.commit();
            return animal;
        } catch (Exception e) {
            if (tx != null) {
                session.getTransaction().rollback();
            }
            throw new RuntimeException(
                    "Error saving animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery(
                    "FROM Animal a WHERE LOWER(a.name) LIKE :name", Animal.class);
            query.setParameter("name",
                    Character.toLowerCase(character) + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error finding animals by name first letter: " + character, e);
        }
    }
}
