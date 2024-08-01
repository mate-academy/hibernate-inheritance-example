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
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        //        try (Session session = sessionFactory.openSession()) {
        //            Query<Animal> query = sessionFactory.openSession().createQuery("from Animal "
        //                    + "where name like \":\"
        //                    or name like \":lowerCharecter\"",
        //                    Animal.class);
        //            query.setParameter("upperCharecter", Character.toUpperCase(character) + "%");
        //            query.setParameter("lowerCharecter", Character.toLowerCase(character) + "%");
        //            return query.getResultList();
        //        } catch (Exception e) {
        //            throw new RuntimeException();
        //        }
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    "from Animal where name like :upperCharecter OR name like :lowerCharecter";
            Query<Animal> query = session.createQuery(hql, Animal.class);
            query.setParameter("upperCharecter", Character.toUpperCase(character) + "%");
            query.setParameter("lowerCharecter", Character.toLowerCase(character) + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
