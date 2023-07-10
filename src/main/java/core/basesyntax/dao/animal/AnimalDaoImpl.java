package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
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
        try (Session session = sessionFactory.openSession()) {
            session.save(animal);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> query = session.createQuery("from Animal a "
                    + "where LOWER(a.name) like :name", Animal.class);
            query.setParameter("name", Character.toLowerCase(character) + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
