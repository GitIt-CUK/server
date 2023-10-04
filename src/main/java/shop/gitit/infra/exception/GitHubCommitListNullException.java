package shop.gitit.infra.exception;

public class GitHubCommitListNullException extends RuntimeException {
    public GitHubCommitListNullException() {}

    public GitHubCommitListNullException(String message) {
        super(message);
    }

    public GitHubCommitListNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitHubCommitListNullException(Throwable cause) {
        super(cause);
    }
}
