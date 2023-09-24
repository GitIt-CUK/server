package shop.gitit.github.exception;

public class GitHubGetRepositoriesInvalidException extends RuntimeException {
    public GitHubGetRepositoriesInvalidException() {}

    public GitHubGetRepositoriesInvalidException(String message) {
        super(message);
    }

    public GitHubGetRepositoriesInvalidException(String message, Throwable cause) {
        super(message, cause);
    }

    public GitHubGetRepositoriesInvalidException(Throwable cause) {
        super(cause);
    }
}
