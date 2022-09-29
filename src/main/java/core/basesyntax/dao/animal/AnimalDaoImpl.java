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
            throw new RuntimeException("Can't save animal " + animal, e);
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
            Query<Animal> findByLetterQuery = session.createQuery("from Animal a "
                    + "where a.name "
                    + "like :firstLetterUpperCase "
                    + "or a.name like :firstLetterLowerCase", Animal.class);
            findByLetterQuery.setParameter("firstLetterUpperCase",
                    String.valueOf(character).toUpperCase() + "%");
            findByLetterQuery.setParameter("firstLetterLowerCase",
                    String.valueOf(character).toLowerCase() + "%");
            return findByLetterQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by letter " + character, e);
        }
    }
}
