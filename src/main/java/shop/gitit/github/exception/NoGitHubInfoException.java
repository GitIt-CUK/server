package shop.gitit.github.exception;

public class NoGitHubInfoException extends RuntimeException {

    public NoGitHubInfoException() {}

    public NoGitHubInfoException(String message) {
        super(message);
    }

    public NoGitHubInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoGitHubInfoException(Throwable cause) {
        super(cause);
    }
}
