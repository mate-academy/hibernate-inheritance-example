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
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Animal a WHERE a.name LIKE :upperTemplate "
                            + "OR  a.name LIKE :lowerTemplate ", Animal.class)
                    .setParameter("lowerTemplate", String.valueOf(character).toLowerCase() + "%")
                    .setParameter("upperTemplate", String.valueOf(character).toUpperCase() + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
