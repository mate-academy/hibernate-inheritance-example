package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.criteria.HibernateCriteriaBuilder;
import org.hibernate.query.criteria.JpaCriteriaQuery;

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
            throw new RuntimeException("Can't save animal",e);
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
            HibernateCriteriaBuilder criteriaBuilder =
                    session.getCriteriaBuilder();
            JpaCriteriaQuery<Animal> query =
                    criteriaBuilder.createQuery(Animal.class);
            Root<Animal> root = query.from(Animal.class);

            String upperCase = character.toString().toUpperCase();
            Predicate name = criteriaBuilder
                    .like(criteriaBuilder.upper(root.get("name")), upperCase + "%");
            query.where(name);
            return session.createQuery(query).list();
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal",e);
        }
    }
}
