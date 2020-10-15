package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao<Animal> implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        return super.save(animal);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> userQuery =
                    session.createQuery("FROM Animal WHERE name LIKE :character",
                            Animal.class);
            userQuery.setParameter("character", character.toString() + "%");
            return userQuery.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find any animals with name starts"
                    + " with " + character, e);
        }
    }
}
