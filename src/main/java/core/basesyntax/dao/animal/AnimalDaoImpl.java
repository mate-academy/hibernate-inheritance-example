package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
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
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession().getSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save animal " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession().getSession()) {
            Query<Animal> findByNameFirstLetterQuery = session.createQuery("from Animal a "
                    + "where a.name like :key ", Animal.class);
            findByNameFirstLetterQuery.setParameter("key", character + "%");
            return findByNameFirstLetterQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find by name first letter animal "
                    + character, e);
        }
    }
}
