package shop.gitit.member.exception;

public class StolenTokenException extends RuntimeException {

    public StolenTokenException() {}

    public StolenTokenException(String message) {
        super(message);
    }

    public StolenTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public StolenTokenException(Throwable cause) {
        super(cause);
    }
}
