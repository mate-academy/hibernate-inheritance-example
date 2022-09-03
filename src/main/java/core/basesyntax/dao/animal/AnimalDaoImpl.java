package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.ObjectDao;
import core.basesyntax.dao.ObjectDaoImpl;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {

    private final ObjectDao<Animal> objectDao = new ObjectDaoImpl<>(sessionFactory);

    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        return objectDao.save(animal);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery("FROM Animal a "
                    + "WHERE LOWER(a.name) LIKE :format", Animal.class);
            query.setParameter("format", Character.toLowerCase(character) + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't get animal by first letter " + character, e);
        }
    }
}
