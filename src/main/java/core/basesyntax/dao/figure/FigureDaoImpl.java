package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.dao.animal.AnimalDao;
import core.basesyntax.model.figure.Circle;
import core.basesyntax.model.figure.Figure;
import java.util.List;

import core.basesyntax.model.figure.Triangle;
import core.basesyntax.model.zoo.Animal;
import core.basesyntax.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure>
        extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
            Session session = null;
            Transaction transaction = null;
            try {
                session =  sessionFactory.openSession();
                transaction = session.beginTransaction();
                session.persist(figure);
                transaction.commit();
            } catch (RuntimeException e) {
                if (transaction != null) {
                    transaction.rollback();
                }
                throw new RuntimeException(
                        "Can't insert figure " + figure + " into DB, rolling back transaction.", e);
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {

        if (clazz.equals(Circle.class)) {
            try (Session session = sessionFactory.openSession()) {
                Query<T> query = session.createQuery("FROM circles c WHERE c.color = :color", clazz);
                query.setParameter("color", color);
                return query.getResultList();
            }
        }
        if (clazz.equals(Triangle.class)) {//źle
            try(Session session = sessionFactory.openSession()) {
                Query<T> query = session.createQuery("FROM triangles c WHERE c.color = :color", clazz);
                query.setParameter("color", color); // FROM figure, WHERE color AND clazz parametr
                return query.getResultList();
            }
        }
        return null;
    }
}
