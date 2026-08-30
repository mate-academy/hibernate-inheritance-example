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
        Transaction tx = null;
        try (Session session = sessionFactory.openSession()) {
            tx = session.beginTransaction();
            session.persist(animal);
            tx.commit();
            return animal;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String prefix = character.toString().toUpperCase() + "%";
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                            "FROM Animal a WHERE UPPER(a.name) LIKE :prefix", Animal.class)
                    .setParameter("prefix", prefix)
                    .getResultList();
        }
    }
}
