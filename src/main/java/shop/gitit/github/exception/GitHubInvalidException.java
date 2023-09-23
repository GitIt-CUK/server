package shop.gitit.github.exception;

public class GitHubInvalidException extends RuntimeException {
    public GitHubInvalidException() {}

    public GitHubInvalidException(String message) {
        super(message);
    }

    public GitHubInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitHubInvalidException(Throwable cause) {
        super(cause);
    }
}
