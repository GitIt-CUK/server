package shop.gitit.infra.exception;

public class FailConvertToShortInfoException extends RuntimeException {
    public FailConvertToShortInfoException() {}

    public FailConvertToShortInfoException(String message) {
        super(message);
    }

    public FailConvertToShortInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailConvertToShortInfoException(Throwable cause) {
        super(cause);
    }
}
