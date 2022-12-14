package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        // try (Session session = sessionFactory.openSession()) {
        //     return session.createQuery("FROM Animal a WHERE a.name LIKE :like", Animal.class)
        //             .setParameter("like", "'" + Character.toLowerCase(character) + "%'")
        //             .getResultList();
        // }
        try (Session session = sessionFactory.openSession()) {
            List<Animal> animals = session.createQuery("FROM Animal", Animal.class).getResultList();
            List<Animal> resultList = new ArrayList<>();
            for (Animal animal : animals) {
                if (animal.getName().toUpperCase().charAt(0) == character) {
                    resultList.add(animal);
                }
            }
            return resultList;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
