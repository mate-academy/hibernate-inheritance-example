package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.zoo.Animal;
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(animal);
        transaction.commit();
        session.close();
        return animal;
    }

    @Override
    public List<Animal> findByNameFirstLetter(Character character) {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Animal a where lower(a.name) "
                + "LIKE lower(:startCharackter) ");
        query.setParameter("startCharackter", character.toString().toLowerCase() + "%");
        List resultList = query.getResultList();
        return resultList;
    }
}
