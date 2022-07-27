package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.Collections;
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
            session.save(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot save " + animal + " in DB");
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
            String lowLetterParam = character.toString().toLowerCase() + "%";
            String upLetterParam = character.toString().toUpperCase() + "%";
            Query<Animal> query = session.createQuery(
                    "from Animal a WHERE a.name LIKE :low OR a.name LIKE :up", Animal.class);
            query.setParameter("low", lowLetterParam);
            query.setParameter("up", upLetterParam);
            List<Animal> result = query.getResultList();
            return result.size() == 0 ? Collections.emptyList() : result;
        } catch (Exception e) {
            throw new RuntimeException("Cannot get list of animals from DB by first letter="
                    + character);
        }
    }
}
