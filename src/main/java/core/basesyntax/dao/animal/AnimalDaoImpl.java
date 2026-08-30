package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exceptions.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        return addEntity(animal);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery("FROM Animal a "
                    + "WHERE LOWER (a.name) LIKE :prefix", Animal.class);
            query.setParameter("prefix", character.toString().toLowerCase() + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new DataProcessingException("Can't find animal by character: " + character, e);
        }
    }
}
