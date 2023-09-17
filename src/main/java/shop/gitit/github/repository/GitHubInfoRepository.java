package shop.gitit.github.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import shop.gitit.github.domain.GitHubInfo;

public interface GitHubInfoRepository extends JpaRepository<GitHubInfo, Long> {

    Optional<GitHubInfo> findGitHubInfoByMemberId(long memberId);
}
