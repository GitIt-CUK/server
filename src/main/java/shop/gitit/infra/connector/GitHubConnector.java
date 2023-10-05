package shop.gitit.infra.connector;

import java.io.IOException;
import org.kohsuke.github.GitHub;
import org.kohsuke.github.GitHubBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.gitit.infra.exception.FailConnectToGitHubException;

@Component
public class GitHubConnector {

    @Value("${github.client.oauth.token}")
    private String githubOAuthToken;

    public GitHub getGithub() {
        try {
            GitHub gitHub = new GitHubBuilder().withOAuthToken(githubOAuthToken).build();
            gitHub.checkApiUrlValidity();
            return gitHub;
        } catch (IOException e) {
            throw new FailConnectToGitHubException(e);
        }
    }
}
