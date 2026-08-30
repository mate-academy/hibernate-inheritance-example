package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.DaoOperation;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    private final DaoOperation<Animal> animalDaoOperation =
            new DaoOperation<>();

    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        return animalDaoOperation.add(animal, sessionFactory);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> animalQuery = session.createQuery("FROM Animal a "
                    + "WHERE LOWER(a.name) like :letter",
                    Animal.class);
            animalQuery.setParameter("letter", Character.toLowerCase(character) + "%");
            return animalQuery.getResultList();
        }
    }
}
