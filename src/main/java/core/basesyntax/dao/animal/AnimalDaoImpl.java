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
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException(e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            String nameFinder = character.toString() + "%";
            String lowerCaseName = nameFinder.toLowerCase();
            Query<Animal> query = session
                    .createQuery("from Animal a "
                            + "WHERE a.name LIKE :nameFinder "
                            + "OR a.name LIKE :lowerCaseName", Animal.class);
            query.setParameter("nameFinder", nameFinder);
            query.setParameter("lowerCaseName", lowerCaseName);
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find an order by user", e);
        }
    }
}
