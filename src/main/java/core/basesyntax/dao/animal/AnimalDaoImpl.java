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
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert a animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Animal> query =
                    criteriaBuilder.createQuery(Animal.class);
            Root<Animal> root = query.from(Animal.class);
            Predicate predicate =
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                            (character + "%").toLowerCase());
            query.where(predicate);
            return session.createQuery(query).getResultList();*/
            Query<Animal> query = session.createQuery("FROM Animal a "
                    + "WHERE LOWER (a.name) LIKE :char", Animal.class);
            query.setParameter("char", (character + "%").toLowerCase());
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get animals by character: " + character, e);
        }
    }
}
