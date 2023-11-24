package shop.gitit.infra.exception;

public class FailGetGitHubAccessTokenException extends RuntimeException {

    public FailGetGitHubAccessTokenException() {}

    public FailGetGitHubAccessTokenException(String message) {
        super(message);
    }

    public FailGetGitHubAccessTokenException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailGetGitHubAccessTokenException(Throwable cause) {
        super(cause);
    }
}
