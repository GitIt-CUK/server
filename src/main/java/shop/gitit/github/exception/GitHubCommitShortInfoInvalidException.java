package shop.gitit.github.exception;

public class GitHubCommitShortInfoInvalidException extends RuntimeException {
    public GitHubCommitShortInfoInvalidException() {}

    public GitHubCommitShortInfoInvalidException(String message) {
        super(message);
    }

    public GitHubCommitShortInfoInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitHubCommitShortInfoInvalidException(Throwable cause) {
        super(cause);
    }
}
