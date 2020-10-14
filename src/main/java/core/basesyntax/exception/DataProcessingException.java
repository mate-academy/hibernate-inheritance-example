package core.basesyntax.exception;

import org.hibernate.HibernateException;

public class DataProcessingException extends HibernateException {
    public DataProcessingException(String message, HibernateException e) {
        super(message, e);
    }
}
