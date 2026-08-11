package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Dog;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class DogDaoImpl extends AbstractDao implements DogDao {
    public DogDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Dog save(Dog dog) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(dog);
            transaction.commit();
            return dog;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save dog: " + dog
                    + " to the database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
