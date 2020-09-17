package core.basesyntax.model.zoo.dao.impl;

import java.util.List;
import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.zoo.Dog;
import core.basesyntax.model.zoo.dao.DogDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class DogDaoImpl implements DogDao {
    @Override
    public Dog save(Dog dog) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(dog);
            transaction.commit();
            return dog;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Couldn't insert cat entity - " + dog, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Dog> findByFirstLetter(Character letter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Dog> query = session.createQuery(
                    "from Dog dog where lower(dog.name) like :letter",
                    Dog.class);
            query.setParameter("letter", letter + "%");
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find dogs which names starts with - "
                    + letter, e);
        }
    }
}
