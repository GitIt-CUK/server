package shop.gitit.github.exception;

public class CommitCountViolationException extends RuntimeException {

    public CommitCountViolationException() {}

    public CommitCountViolationException(String message) {
        super(message);
    }

    public CommitCountViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommitCountViolationException(Throwable cause) {
        super(cause);
    }
}
