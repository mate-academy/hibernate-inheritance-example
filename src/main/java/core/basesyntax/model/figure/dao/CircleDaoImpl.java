package core.basesyntax.model.figure.dao;

import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.machine.Machine;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CircleDaoImpl implements CircleDao {
    @Override
    public Circle save(Circle figure) {
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
            throw new RuntimeException("There was an error inserting circle "
                    + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<Circle> findByColor(String color) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<Circle> criteriaQuery
                    = criteriaBuilder.createQuery(Circle.class);
            Root<Circle> root = criteriaQuery.from(Circle.class);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.equal(root.get("color"), color)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving circles", e);
        }
    }
}
