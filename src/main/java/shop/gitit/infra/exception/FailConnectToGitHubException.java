package shop.gitit.infra.exception;

public class FailConnectToGitHubException extends RuntimeException {
    public FailConnectToGitHubException() {}

    public FailConnectToGitHubException(String message) {
        super(message);
    }

    public FailConnectToGitHubException(String message, Throwable cause) {
        super(message, cause);
    }

    public FailConnectToGitHubException(Throwable cause) {
        super(cause);
    }
}
