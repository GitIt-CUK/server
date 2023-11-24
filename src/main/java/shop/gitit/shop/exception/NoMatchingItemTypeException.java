package shop.gitit.shop.exception;

public class NoMatchingItemTypeException extends RuntimeException {

    public NoMatchingItemTypeException() {}

    public NoMatchingItemTypeException(String message) {
        super(message);
    }

    public NoMatchingItemTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingItemTypeException(Throwable cause) {
        super(cause);
    }
}
