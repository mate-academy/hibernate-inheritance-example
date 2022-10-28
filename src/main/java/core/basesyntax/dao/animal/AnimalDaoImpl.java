package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Animal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class AnimalDaoImpl extends AbstractDao implements AnimalDao {
    public AnimalDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Animal save(Animal animal) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(animal);
            transaction.commit();
            return animal;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't insert animal: " + animal, e);
        }
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        try (Session session = sessionFactory.openSession()) {
            Query<Animal> getAllAnimalsQuery
                    = session.createQuery("from Animal", Animal.class);
            List<Animal> animalsList = getAllAnimalsQuery.getResultList();
            ArrayList<Animal> resultList = new ArrayList<>();
            for (int index = 0; index < animalsList.size(); index++) {
                if (animalsList.get(index).getName().toUpperCase().charAt(0) == character) {
                    resultList.add(animalsList.get(index));
                }
            }
            return resultList;
        } catch (Exception e) {
            throw new DataProcessingException("Can't find animal with name "
                    + "who starts on first letter: " + character, e);
        }
    }
}
