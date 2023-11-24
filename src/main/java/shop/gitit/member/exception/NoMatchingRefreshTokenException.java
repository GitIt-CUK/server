package shop.gitit.member.exception;

public class NoMatchingRefreshTokenException extends RuntimeException {

    public NoMatchingRefreshTokenException() {}

    public NoMatchingRefreshTokenException(String message) {
        super(message);
    }

    public NoMatchingRefreshTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMatchingRefreshTokenException(Throwable cause) {
        super(cause);
    }
}
