package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.time.LocalDateTime;
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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Could not save animal", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery("from Animal a "
                    + "where a.name LIKE :bigChar "
                    + "OR a.name LIKE :lowChar", Animal.class);
            LocalDateTime now = LocalDateTime.now();
            query.setParameter("bigChar", character.toString().toUpperCase() + "%");
            query.setParameter("lowChar", character.toString().toLowerCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Could not find machine by age", e);
        }
    }
}
