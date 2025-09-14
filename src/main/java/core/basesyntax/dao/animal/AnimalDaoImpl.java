package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

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
            throw new RuntimeException("Can't add animal " + animal + " to DB", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Animal a "
                            + "where a.name like :nameBeginsWithUpperCase "
                            + "or a.name like :nameBeginsWithLowerCase", Animal.class)
                    .setParameter("nameBeginsWithUpperCase", String
                            .valueOf(character).toUpperCase() + "%")
                    .setParameter("nameBeginsWithLowerCase", String
                            .valueOf(character).toLowerCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get list of animals, whose "
                    + "names begin with th letter " + character + " in DB", e);
        }
    }
}
