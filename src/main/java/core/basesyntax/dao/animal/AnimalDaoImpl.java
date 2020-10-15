package core.basesyntax.dao.animal;

import core.basesyntax.dao.GeneralDaoImpl;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends GeneralDaoImpl<Animal> implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(
                    "FROM Animal a "
                            + "WHERE a.name LIKE :character", Animal.class)
                    .setParameter("character", character + "%")
                    .getResultList();
        }
    }
}
