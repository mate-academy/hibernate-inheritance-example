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
        Session session = null;
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
            throw new RuntimeException("Can't save in data base this animal: "
                    + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        String hql = "SELECT a FROM Animal a "
                + "WHERE a.name LIKE :latter";
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery(hql, Animal.class);
            query.setParameter("latter", character + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find  animals by this letter: '"
                    + character + "'", e);
        }
    }
}
