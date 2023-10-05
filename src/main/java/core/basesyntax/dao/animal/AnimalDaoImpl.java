package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        try {
            sessionFactory.inTransaction(s -> s.persist(animal));
            return animal;
        } catch (Exception e) {
            throw new RuntimeException("Can't add animal to the DB", e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try {
            return sessionFactory.fromSession(s -> s.createQuery("FROM Animal a "
                            + "WHERE a.name ILIKE CONCAT(:character, '%')", Animal.class)
                    .setParameter("character", character)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("No animal starts with " + character, e);
        }
    }
}
