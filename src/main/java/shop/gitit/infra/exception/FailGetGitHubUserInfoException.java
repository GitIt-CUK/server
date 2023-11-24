package shop.gitit.infra.exception;

public class FailGetGitHubUserInfoException extends RuntimeException {

    public FailGetGitHubUserInfoException() {}

    public FailGetGitHubUserInfoException(String message) {
        super(message);
    }

    public FailGetGitHubUserInfoException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailGetGitHubUserInfoException(Throwable cause) {
        super(cause);
    }
}
