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
        Transaction transaction = null;
        try {
            Session session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal: " + animal);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String firstChar = String.valueOf(character);
        String firstCharLowerCase = firstChar.toLowerCase();
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> sessionQuery
                    = session.createQuery("from Animal a "
                    + "where substring(a.name, 1, 1) = :firstChar "
                    + "or substring(a.name, 1, 1) = :lowerChar", Animal.class);
            sessionQuery.setParameter("firstChar", firstChar);
            sessionQuery.setParameter("lowerChar", firstCharLowerCase);
            return sessionQuery.getResultList();
        }
    }
}
