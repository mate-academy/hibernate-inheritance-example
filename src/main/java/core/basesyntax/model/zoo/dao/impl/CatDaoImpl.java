package core.basesyntax.model.zoo.dao.impl;

import java.util.List;
import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.zoo.Cat;
import core.basesyntax.model.zoo.dao.CatDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class CatDaoImpl implements CatDao {
    @Override
    public Cat save(Cat cat) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(cat);
            transaction.commit();
            return cat;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot insert cat entity - " + cat, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Cat> findByFirstLetter(Character letter) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Cat> query = session.createQuery(
                    "from Cat cat where lower(cat.name) like :letter",
                    Cat.class);
            query.setParameter("letter", letter + "%");
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Failure: can't find shopping cart for user "
                    + letter + ".", e);
        }
    }
}
