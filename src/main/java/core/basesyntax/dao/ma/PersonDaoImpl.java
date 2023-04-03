package core.basesyntax.dao.ma;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.ma.Person;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class PersonDaoImpl extends AbstractDao implements PersonDao {
    public PersonDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public Person save(Person entity) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(entity);
            transaction.commit();
            return entity;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't insert a entity: " + entity, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
