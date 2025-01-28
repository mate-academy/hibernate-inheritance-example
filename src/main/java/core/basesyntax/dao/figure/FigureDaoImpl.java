package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        Session session = null;
        Transaction transaction = null;
        try{
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.persist(figure);
            transaction.commit();
            return figure;
        } catch (Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            throw new RuntimeException("Save figure failed" + figure,e);
        } finally {
            if (session != null){
                session.close();
            }
        }
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        try(Session session = sessionFactory.openSession()){
            Query<T> query = session.createQuery("FROM " + clazz.getSimpleName() + " c "
                    + "WHERE c.color = :color", clazz);
            query.setParameter("color", color);
            return query.getResultList();

        }
    }
}
