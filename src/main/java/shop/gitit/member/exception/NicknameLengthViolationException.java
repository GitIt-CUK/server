package shop.gitit.member.exception;

public class NicknameLengthViolationException extends RuntimeException {

    public NicknameLengthViolationException() {}

    public NicknameLengthViolationException(String message) {
        super(message);
    }

    public NicknameLengthViolationException(String message, Throwable cause) {
        super(message, cause);
    }

    public NicknameLengthViolationException(Throwable cause) {
        super(cause);
    }
}
