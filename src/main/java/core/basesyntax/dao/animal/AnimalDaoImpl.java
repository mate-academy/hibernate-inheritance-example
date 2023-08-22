package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    private static final String NAME_UPPERCASE_PARAMETER = "uppercase_name";
    private static final String NAME_LOWERCASE_PARAMETER = "lowercase_name";
    private static final String PATTERN_FOR_FIRST_LETTER = "%";

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
            throw new RuntimeException("Can't insert animal: " + animal, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "FROM Animal a"
                    + " WHERE a.name LIKE :" + NAME_UPPERCASE_PARAMETER
                    + " OR a.name LIKE :" + NAME_LOWERCASE_PARAMETER;
            Query<Animal> getMachineByAgeQuery =
                    session.createQuery(hql, Animal.class);
            getMachineByAgeQuery.setParameter(NAME_UPPERCASE_PARAMETER,
                    character.toString().toUpperCase() + PATTERN_FOR_FIRST_LETTER);
            getMachineByAgeQuery.setParameter(NAME_LOWERCASE_PARAMETER,
                    character.toString().toLowerCase() + PATTERN_FOR_FIRST_LETTER);
            return getMachineByAgeQuery.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find an animal by name which starts with letter: "
                    + character, e);
        }
    }
}
