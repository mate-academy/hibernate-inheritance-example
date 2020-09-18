package core.basesyntax.model.zoo.dao.impl;

import java.util.List;
import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.model.zoo.dao.AnimalDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AnimalDaoImpl implements AnimalDao {
    @Override
    public Animal save(Animal animal) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot insert "
                    + animal.getClass().getSimpleName() + " entity - " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByFirstLetter(Character letter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Animal> query = session.createQuery(
                    "from Animal a where lower(a.name) like :letter",
                    Animal.class);
            query.setParameter("letter", letter + "%");
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find cats which names starts with - "
                    + letter, e);
        }
    }
}
