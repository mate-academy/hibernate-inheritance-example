package core.basesyntax.dao;

import java.util.function.Function;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class AbstractDao {
    protected final SessionFactory sessionFactory;

    protected AbstractDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected <T> T performReturnWithinTx(Function<Session, T> function) {
        return sessionFactory.fromTransaction(function);
    }

    protected <T> T performReturnWithoutTx(Function<Session, T> function) {
        return sessionFactory.fromSession(function);
    }
}
