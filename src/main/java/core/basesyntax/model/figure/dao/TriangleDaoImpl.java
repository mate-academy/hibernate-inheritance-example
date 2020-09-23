package core.basesyntax.model.figure.dao;

import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class TriangleDaoImpl implements TriangleDao {

    @Override
    public Triangle save(Triangle figure) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("There was an error inserting triangle "
                    + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Triangle> findByColor(String color) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Triangle> criteriaQuery
                    = criteriaBuilder.createQuery(Triangle.class);
            Root<Triangle> root = criteriaQuery.from(Triangle.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.equal(root.get("color"), color)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving triangles", e);
        }
    }
}
