package shop.gitit.github.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.github.service.port.in.CreateGitHubInfoUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class CreateGitHubUserInfoService implements CreateGitHubInfoUsecase {

    private final GitHubInfoRepository gitHubInfoRepository;

    @Override
    public void createGitHubInfo(Long memberId) {
        if (gitHubInfoRepository.findGitHubInfoByMemberId(memberId).isEmpty()) {
            gitHubInfoRepository.save(new GitHubInfo(memberId));
        }
    }
}
