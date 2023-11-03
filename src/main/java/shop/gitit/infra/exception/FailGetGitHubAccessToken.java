package shop.gitit.infra.exception;

public class FailGetGitHubAccessToken extends RuntimeException {

    public FailGetGitHubAccessToken() {}

    public FailGetGitHubAccessToken(String message) {
        super(message);
    }

    public FailGetGitHubAccessToken(String message, Throwable cause) {
        super(message, cause);
    }

    public FailGetGitHubAccessToken(Throwable cause) {
        super(cause);
    }
}
