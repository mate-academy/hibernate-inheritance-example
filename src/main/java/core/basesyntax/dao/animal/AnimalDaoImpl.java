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
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character letter) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> getTypeAnimaQuery = session.createQuery("SELECT a FROM Animal a "
                    + "WHERE a.name LIKE :name", Animal.class);
            getTypeAnimaQuery.setParameter("name", Character.toLowerCase(letter) + "%");
            return getTypeAnimaQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find animal name by first letter "
                    + letter, e);
        }
    }
}
