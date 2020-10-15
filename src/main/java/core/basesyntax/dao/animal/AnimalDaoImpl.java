package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class AnimalDaoImpl extends AbstractDao<Animal> implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("from Animal "
                    + "where name like :name", Animal.class)
                    .setParameter("name", character + "%")
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can not find animal by its first letter " + character, e);
        }
    }
}
