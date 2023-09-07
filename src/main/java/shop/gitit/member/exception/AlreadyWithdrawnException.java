package shop.gitit.member.exception;

public class AlreadyWithdrawnException extends RuntimeException {

    public AlreadyWithdrawnException() {}

    public AlreadyWithdrawnException(String message) {}

    public AlreadyWithdrawnException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyWithdrawnException(Throwable cause) {
        super(cause);
    }
}
