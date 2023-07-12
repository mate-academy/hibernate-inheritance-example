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
            throw new RuntimeException("Can't save animal: " + animal);
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
            String lowerCasePattern = Character.toLowerCase(character) + "%";
            String upperCasePattern = Character.toUpperCase(character) + "%";
            return session.createQuery("from Animal where name like :lowChar or name like :upChar",
                            Animal.class)
                    .setParameter("lowChar", lowerCasePattern)
                    .setParameter("upChar", upperCasePattern)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Can't find animals with name starts with \"" + character + "\"");
        }
    }
}
