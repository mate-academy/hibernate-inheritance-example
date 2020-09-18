package core.basesyntax.model.figure.dao.impl;

import java.util.List;
import core.basesyntax.model.HibernateUtil;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.figure.dao.TriangleDao;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class TriangleDaoImpl implements TriangleDao {
    @Override
    public Triangle save(Triangle triangle) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(triangle);
            transaction.commit();
            return triangle;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Cannot insert " + triangle.getClass().getSimpleName()
                    + " entity - " + triangle, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Triangle> getByColor(String color) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Triangle> query = session.createQuery(
                    "from Triangle t where t.color = :color");
            query.setParameter("color", color);
            return query.getResultList();
        } catch (HibernateException e) {
            throw new RuntimeException("Can't find Triangles which have color  - "
                    + color, e);
        }
    }
}
