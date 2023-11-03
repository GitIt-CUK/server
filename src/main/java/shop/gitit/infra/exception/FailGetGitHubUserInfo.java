package shop.gitit.infra.exception;

public class FailGetGitHubUserInfo extends RuntimeException {

    public FailGetGitHubUserInfo() {}

    public FailGetGitHubUserInfo(String message) {
        super(message);
    }

    public FailGetGitHubUserInfo(String message, Throwable cause) {
        super(message, cause);
    }

    public FailGetGitHubUserInfo(Throwable cause) {
        super(cause);
    }
}
