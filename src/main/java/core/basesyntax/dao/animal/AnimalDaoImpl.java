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
        String lower = (character + "%").toLowerCase();
        String upper = (character + "%").toUpperCase();
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Animal a "
                            + "where a.name like :a or a.name like :b", Animal.class)
                    .setParameter("a", lower).setParameter("b", upper).list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find name with first letter: " + character, e);
        }
    }
}
