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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal to DB: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query findByNameFirstLetterQuery =
                    session.createQuery("FROM Animal n "
                            + "WHERE n.name LIKE :characterPrefix OR n.name "
                            + "LIKE :lowercaseCharacterPrefix", Animal.class);
            findByNameFirstLetterQuery.setParameter("characterPrefix", character + "%");
            findByNameFirstLetterQuery.setParameter("lowercaseCharacterPrefix",
                    Character.toLowerCase(character) + "%");
            return findByNameFirstLetterQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animals "
                    + "whose name starts with: " + character, e);
        }
    }
}
