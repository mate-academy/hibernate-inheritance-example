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
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = (Transaction) session.beginTransaction();
        try {
            session.persist(animal);
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't save animal", e);
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        try {
            List<Animal> result = session.createQuery(
                            "FROM Animal WHERE LOWER(name) LIKE LOWER(CONCAT(:character, '%'))",
                            Animal.class)
                    .setParameter("character", character.toString().toLowerCase())
                    .getResultList();
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException("Can't find animals", e);
        }
    }
}
