package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.exception.DataProcessingException;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import java.util.Random;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
            session = getSession();
            transaction = session.beginTransaction();
            session.save(figure);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            throw new DataProcessingException(
                    String.format("Can't save %s to DB", figure), e);
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        return new Random().nextBoolean()
                ? findByColorHql(color, clazz)
                : findByColorCriteriaApi(color, clazz);
    }

    private List<T> findByColorHql(String color, Class<T> clazz) {
        try (Session session = getSession()) {
            System.out.println("Greeting from HQL");
            return session.createQuery(String.format("from %s where color = :color",
                            clazz.getName()), clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find figures by %s", color), e);
        }
    }

    private List<T> findByColorCriteriaApi(String color, Class<T> clazz) {
        try (Session session = getSession()) {
            System.out.println("Greeting from Criteria API");
            CriteriaBuilder cb = session.getCriteriaBuilder();
            CriteriaQuery<T> query = cb.createQuery(clazz);
            Root<T> root = query.from(clazz);
            Predicate colorPredicate = cb.equal(root.get("color"), color);
            query.select(root).where(colorPredicate);
            return session.createQuery(query).getResultList();
        } catch (Exception e) {
            throw new DataProcessingException(
                    String.format("Can't find figures by %s", color), e);
        }
    }

    private Session getSession() {
        return sessionFactory.openSession();
    }
}
