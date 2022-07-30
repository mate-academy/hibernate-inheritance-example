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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(figure);
        transaction.commit();
        session.close();
        return figure;
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        Session session = sessionFactory.openSession();
        String str = "from " + clazz.getName();
        Query query = session.createQuery(str + " f where f.color = :color ");
        query.setParameter("color", color);
        List<T> resultList = (List<T>) query.getResultList();
        return resultList;
    }
}
