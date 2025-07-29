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
        try (var session = sessionFactory.openSession()) {
            var transaction = session.beginTransaction();
            session.persist(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            throw new RuntimeException("Can`t save animal!: " + animal, e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (var session = sessionFactory.openSession()) {
            var query =
                    session.createQuery("FROM Animal a WHERE UPPER(a.name) LIKE :prefix",
                            Animal.class);
            query.setParameter("prefix", character + "%");
            return query.getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can`t find animals by first letter!: " + character, e);
        }
    }
}
