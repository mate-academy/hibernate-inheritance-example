package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        EntityTransaction entityTransaction = null;

        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(figure);
            entityTransaction.commit();

            return figure;
        } catch (Exception e) {
            if (entityTransaction != null && entityTransaction.isActive()) {
                entityTransaction.rollback();
            }

            throw new RuntimeException("Can't save figure " + figure, e);
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try (EntityManager entityManager = this.sessionFactory.createEntityManager()) {
            return entityManager.createQuery("from " + clazz.getSimpleName()
                                    + " WHERE color = :color",
                            clazz)
                    .setParameter("color", color)
                    .getResultList();
        } catch (Exception e) {
            throw new RuntimeException("Can't find figure by color " + color, e);
        }
    }
}
