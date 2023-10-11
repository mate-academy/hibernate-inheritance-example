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
            return sessionFactory.fromTransaction(session -> {
                session.persist(animal);
                return animal;
            });
        } catch (Exception e) {
            throw new RuntimeException("Can't add animal to DB: " + animal, e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try {
            return sessionFactory.fromSession(session -> session.createQuery("from Animal a "
                            + "where a.name ilike concat(:character, '%')", Animal.class)
                    .setParameter("character", character)
                    .getResultList());
        } catch (Exception e) {
            throw new RuntimeException("Can't find animal by this character: " + character,e);
        }
    }
}
