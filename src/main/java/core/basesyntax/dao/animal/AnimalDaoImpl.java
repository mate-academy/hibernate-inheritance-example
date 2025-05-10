package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.ArrayList;
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
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't save animal: "
                    + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "SELECT a from Animal a WHERE a.name LIKE :character";
            Query<Animal> query1 = session.createQuery(hql, Animal.class);
            query1.setParameter("character", character + "%");
            List<Animal> resultList = new ArrayList<>(query1.getResultList());
            Query<Animal> query2 = session.createQuery(hql, Animal.class);
            query2.setParameter("character", character.toString().toLowerCase() + "%");
            resultList.addAll(query2.getResultList());
            return resultList;
        } catch (Exception e) {
            throw new RuntimeException("Can't get animals by character: "
                    + character, e);
        }
    }
}
