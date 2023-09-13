package shop.gitit.payment.exception;

public class PointViolationException extends RuntimeException {

    public PointViolationException() {}

    public PointViolationException(String message) {
        super(message);
    }

    public PointViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointViolationException(Throwable cause) {
        super(cause);
    }
}
