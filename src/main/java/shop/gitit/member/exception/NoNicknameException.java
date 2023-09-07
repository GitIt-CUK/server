package shop.gitit.member.exception;

public class NoNicknameException extends RuntimeException {

    public NoNicknameException() {}

    public NoNicknameException(String message) {
        super(message);
    }

    public NoNicknameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoNicknameException(Throwable cause) {
        super(cause);
    }
}
