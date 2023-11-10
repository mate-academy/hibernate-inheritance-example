package core.basesyntax.exception;

import java.util.function.Supplier;

public class DataProcessingException extends RuntimeException {
    public DataProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataProcessingException(String message) {
        super(message);
    }
}
