package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        return super.saveEntity(animal);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Animal "
                            + "where LOWER(name) like LOWER(:letterPattern)", Animal.class)
                    .setParameter("letterPattern", character + "%")
                    .getResultList();
        } catch (RuntimeException e) {
            throw new RuntimeException("Failed to find animals by name first letter = ["
                    + character + "]", e);
        }
    }
}
