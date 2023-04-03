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
    public Animal save(Animal entity) {

        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert a entity: " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        if (character == null) {
            throw new IllegalArgumentException("Character cannot be null.");
        }
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery("FROM Animal a "
                    + "WHERE LOWER(a.name) LIKE :c", Animal.class);
            query.setParameter("c", character.toString().toLowerCase() + "%");
            return query.list();
        } catch (Exception e) {
            throw new RuntimeException("Failed method findByNameFirstLetter() ", e);
        }
    }
}
