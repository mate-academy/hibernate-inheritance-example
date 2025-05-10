package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import core.basesyntax.model.figure.Triangle;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Session session = null;
        Transaction transaction = null;

        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new RuntimeException("Can't create figure " + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        if (clazz.equals(Triangle.class)) {
            try (Session session = sessionFactory.openSession()) {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<Triangle> criteriaQuery = criteriaBuilder.createQuery(Triangle.class);
                Root<Triangle> root = criteriaQuery.from(Triangle.class);
                Predicate colorPredicate = criteriaBuilder.like(root.get("color"), color);
                criteriaQuery.where(colorPredicate);
                return (List<T>) session.createQuery(criteriaQuery).getResultList();
            } catch (Exception e) {
                throw new RuntimeException("Can't get figure by color", e);
            }
        }
        if (clazz.equals(Circle.class)) {
            try (Session session = sessionFactory.openSession()) {
                CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
                CriteriaQuery<Circle> criteriaQuery = criteriaBuilder.createQuery(Circle.class);
                Root<Circle> root = criteriaQuery.from(Circle.class);
                Predicate colorPredicate = criteriaBuilder.like(root.get("color"), color);
                criteriaQuery.where(colorPredicate);
                return (List<T>) session.createQuery(criteriaQuery).getResultList();
            } catch (Exception e) {
                throw new RuntimeException("Can't get Circle by color", e);
            }
        }
        return null;
    }
}
