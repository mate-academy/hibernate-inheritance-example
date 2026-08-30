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
        return (Animal) sessionFactory.getCurrentSession()
                .save(animal);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Animal WHERE name LIKE :pattern", Animal.class)
                .setParameter("pattern", character + "%")
                .getResultList();
    }
}
