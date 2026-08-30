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
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (RuntimeException e) {
            throw new RuntimeException("Error saving animal", e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery(
                    "FROM Animal "
                            + "WHERE LOWER(name) LIKE LOWER(:character)");
            query.setParameter("character", character + "%");
            return query.list();
        } catch (RuntimeException e) {
            throw new RuntimeException("Error finding animal", e);
        }
    }
}
