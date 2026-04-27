package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import java.util.Objects;
import org.hibernate.HibernateException;
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
        Objects.requireNonNull(animal, "Animal can't be null");
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save an animal " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        Objects.requireNonNull(character, "character can't be null");
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery(
                    "FROM Animal a WHERE LOWER(a.name) like LOWER(:char)", Animal.class);
            query.setParameter("char", character + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by character " + character, e);
        }
    }
}
