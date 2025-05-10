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
        return performReturnWithinTx(session -> {
            session.persist(animal);
            return animal;
        });
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        return performReturnWithoutTx(session ->
            session.createQuery("""
                        from Animal a
                        where lower(a.name) LIKE lower(CONCAT(:char, '%'))""", Animal.class
                    )
                    .setParameter("char", character)
                    .getResultList()
        );
    }
}
