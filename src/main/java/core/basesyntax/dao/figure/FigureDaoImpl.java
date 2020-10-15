package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exeptions.DataProcessingException;
import core.basesyntax.model.figure.Figure;
import java.util.List;

import core.basesyntax.model.machine.Machine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
            return figure;
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException("Can't add figure"
                    + figure, e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (Session session = sessionFactory.openSession()) {
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> orderCriteriaQuery =
                    criteriaBuilder.createQuery(clazz);
            Root<T> orderRoot =
                    orderCriteriaQuery.from(clazz);
            Predicate predicate =
                    criteriaBuilder.equal(orderRoot.get("color"), color);
            orderCriteriaQuery.select(orderRoot).where(predicate);
            return session.createQuery(orderCriteriaQuery.distinct(true)).getResultList();
        }
    }
}
