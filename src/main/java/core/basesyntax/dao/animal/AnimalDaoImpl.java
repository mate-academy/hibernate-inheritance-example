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
        sessionFactory.inTransaction(session -> session.persist(animal));
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        Session session = sessionFactory.openSession();
        return session.createQuery("from Animal a where LOWER(a.name) LIKE '"
                        + Character.toLowerCase(character) + "%'", Animal.class)
                .getResultList();
    }
}
