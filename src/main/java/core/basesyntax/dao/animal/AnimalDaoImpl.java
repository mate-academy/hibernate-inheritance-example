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
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            session.persist(animal);
            tx.commit();
            return animal;
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (var session = getSession()) {
            var tx = session.beginTransaction();
            var query = session.createQuery(
                    "FROM Animal a WHERE upper(a.name) LIKE :firstLetter",
                    Animal.class
            );
            query.setParameter("firstLetter", (character + "%").toUpperCase());
            List<Animal> result = query.getResultList();
            tx.commit();
            return result;
        }
    }
}
