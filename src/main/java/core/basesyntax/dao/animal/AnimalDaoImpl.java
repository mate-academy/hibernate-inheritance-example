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
        Session session = sessionFactory.getCurrentSession();
        session.persist(animal);
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Animal WHERE SUBSTRING(name, 1, 1) = :firstLetter";
        return session.createQuery(hql, Animal.class)
                .setParameter("firstLetter", character.toString())
                .getResultList();
    }

}
