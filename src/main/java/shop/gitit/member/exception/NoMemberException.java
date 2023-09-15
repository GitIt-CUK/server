package shop.gitit.member.exception;

// TODO ExceptionEnum 추가 필요
public class NoMemberException extends RuntimeException {

    public NoMemberException() {}

    public NoMemberException(String message) {
        super(message);
    }

    public NoMemberException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoMemberException(Throwable cause) {
        super(cause);
    }
}
