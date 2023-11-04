package shop.gitit.github.support.githubinfo;

import shop.gitit.github.domain.GitHubInfo;

public class GitHubInfoFixture {

    public static GitHubInfo getGitHubInfo(Long memberId) {
        return new GitHubInfo(memberId);
    }
}
