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
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }

            throw new RuntimeException(String.format(
                    "Can't add animal: %s to DB", animal), e);
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
            Query<Animal> findByFirstLetterQuery = session.createQuery(
                    "FROM Animal a WHERE SUBSTRING(LOWER(a.name) , 1, 1) = :char", Animal.class);
            findByFirstLetterQuery.setParameter("char", String.valueOf(character).toLowerCase());
            return findByFirstLetterQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't get animal with name that starts on letter: " + character, e);
        }
    }
}
