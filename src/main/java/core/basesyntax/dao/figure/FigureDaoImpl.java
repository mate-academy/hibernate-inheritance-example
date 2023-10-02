package core.basesyntax.dao.figure;

import core.basesyntax.dao.AbstractDao;
import core.basesyntax.model.figure.Figure;
import java.util.List;
import org.hibernate.SessionFactory;

public class FigureDaoImpl<T extends Figure> extends AbstractDao implements FigureDao<T> {
    public FigureDaoImpl(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public T save(T figure) {
        return performReturnWithinTx(session -> {
            session.persist(figure);
            return figure;
        });
    }

    @Override
    public List<T> findByColor(String color, Class<T> clazz) {
        return performReturnWithoutTx(session ->
                session.createQuery("from " + clazz.getSimpleName()
                                + " f where f.color = :color", clazz)
                        .setParameter("color", color)
                        .getResultList()
        );
    }
}
