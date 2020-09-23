package core.basesyntax.model.figure.dao;

import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.util.HibernateUtil;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FigureDaoImpl<T extends Figure> implements FigureDao<T> {
    @Override
    public T save(T figure) {
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
            throw new RuntimeException("There was an error inserting figure "
                    + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery
                    = criteriaBuilder.createQuery(clazz);
            Root<T> root = criteriaQuery.from(clazz);
            return session.createQuery(criteriaQuery.where(
                    criteriaBuilder.equal(root.get("color"), color)))
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("There was an error retrieving figures", e);
        }
    }
}
