package shop.gitit.github.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.kohsuke.github.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import shop.gitit.github.exception.GitHubCommitListNullException;
import shop.gitit.github.exception.GitHubCommitShortInfoInvalidException;

@Component
public class GitHubClient {

    @Value("${github.client.oauth.token}")
    private String githubOAuthToken;

    public GitHub getGitHubClient() {
        try {
            GitHub gitHub = new GitHubBuilder().withOAuthToken(githubOAuthToken).build();
            gitHub.checkApiUrlValidity();
            return gitHub;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GHRepository> getRepositories(String githubUserId) {
        try {
            GHUser ghUser = getGitHubClient().getUser(githubUserId);
            List<GHRepository> repositories =
                    ghUser.getRepositories().values().stream().collect(Collectors.toList());
            return repositories;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<GHCommit.ShortInfo> getCommits(String githubUserId) {
        List<GHRepository> ghRepositoryList = getRepositories(githubUserId);
        List<GHCommit.ShortInfo> commits = new ArrayList<GHCommit.ShortInfo>();

        for (GHRepository gr : ghRepositoryList) {
            try {
                commits.addAll(
                        gr.listCommits().toList().stream()
                                .map(
                                        it -> {
                                            try {
                                                return it.getCommitShortInfo();
                                            } catch (IOException e) {
                                                throw new GitHubCommitShortInfoInvalidException(e);
                                            }
                                        })
                                .collect(Collectors.toList()));
            } catch (IOException e) {
                throw new GitHubCommitListNullException(e);
            }
        }

        return commits;
    }
}
