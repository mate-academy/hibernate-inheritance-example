package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import java.time.LocalTime;
import java.time.temporal.ChronoField;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            session = getSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    String.format("Can't save %s to DB", animal), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        long time = LocalTime.now().getLong(ChronoField.MICRO_OF_SECOND);
        return time % 3 == 0
                ? findByNameFirstLetterHql(character) : time % 2 == 0
                ? findByNameFirstLetterCriteriaApi(character)
                : findByNameFirstLetterCriteriaDto(character);
    }

    private List<Animal> findByNameFirstLetterHql(Character character) {
        try (Session session = getSession()) {
            System.out.println("HQL is doing job");
            return session.createQuery("from Animal a "
                                    + "where upper(a.name) like :letter",
                            Animal.class)
                    .setParameter("letter", Character.toUpperCase(character) + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find animals by %s first letter", character), e);
        }
    }

    private List<Animal> findByNameFirstLetterCriteriaApi(Character character) {
        try (Session session = getSession()) {
            System.out.println("Criteria API is doing job");
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<Animal> query = cb.createQuery(Animal.class);
            Root<Animal> animalRoot = query.from(Animal.class);
            Predicate firstLetterPredicate = cb.like(cb.upper(animalRoot.get("name")),
                    Character.toUpperCase(character) + "%");
            query.select(animalRoot).where(firstLetterPredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find animals by %s first letter", character), e);
        }
    }

    private List<Animal> findByNameFirstLetterCriteriaDto(Character character) {
        try (Session session = getSession()) {
            System.out.println("DTO projection is doing job");
            return session.createQuery("select "
                                    + "new core.basesyntax.model.zoo.Animal(a.id, a.age, a.name) "
                                    + "from Animal a "
                                    + "where upper(a.name) "
                                    + "like upper(:letter)",
                            Animal.class)
                    .setParameter("letter", character + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find animals by %s first letter", character), e);
        }
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }
}
