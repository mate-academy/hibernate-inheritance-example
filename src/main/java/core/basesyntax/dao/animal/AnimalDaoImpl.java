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
        } catch (Exception e) {
            throw new RuntimeException("Can't save animal " + animal, e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery("FROM Animal a "
                    + "WHERE a.name LIKE :firstLetter "
                    + "OR a.name LIKE :firstLetterUpper", Animal.class);
            query.setParameter("firstLetter",character.toString().toLowerCase() + "%");
            query.setParameter("firstLetterUpper",character.toString().toUpperCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by name first letter " + character, e);
        }
    }
}
