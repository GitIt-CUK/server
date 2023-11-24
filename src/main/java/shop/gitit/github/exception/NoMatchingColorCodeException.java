package shop.gitit.github.exception;

public class NoMatchingColorCodeException extends RuntimeException {

    public NoMatchingColorCodeException() {}

    public NoMatchingColorCodeException(String message) {
        super(message);
    }

    public NoMatchingColorCodeException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingColorCodeException(Throwable cause) {
        super(cause);
    }
}
