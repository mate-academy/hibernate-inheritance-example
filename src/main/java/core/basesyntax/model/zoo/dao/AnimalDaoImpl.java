package core.basesyntax.model.zoo.dao;

import core.basesyntax.model.util.HibernateUtil;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class AnimalDaoImpl implements AnimalDao {
    @Override
    public Animal save(Animal animal) {
        Session session = null;
        Transaction transaction = null;
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
            throw new RuntimeException("There was an error inserting animal "
                    + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Animal> criteriaQuery
                    = criteriaBuilder.createQuery(Animal.class);
            Root<Animal> root = criteriaQuery.from(Animal.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.like(root.get("name"), character + "%")))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving animals", e);
        }
    }
}
