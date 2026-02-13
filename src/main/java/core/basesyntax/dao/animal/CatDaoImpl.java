package core.basesyntax.dao.animal;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.zoo.Cat;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class CatDaoImpl extends AbstractDao implements CatDao {
    public CatDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Cat save(Cat cat) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(cat);
            transaction.commit();
            return cat;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't save cat: " + cat
                    + " to the database", e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
