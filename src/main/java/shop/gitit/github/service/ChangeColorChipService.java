package shop.gitit.github.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.exception.NoGitHubInfoException;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.github.service.dto.request.ChangeColorChipReqDto;
import shop.gitit.github.service.port.in.ChangeColorChipUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangeColorChipService implements ChangeColorChipUsecase {

    private final GitHubInfoRepository gitHubInfoRepository;

    @Override
    public void changeColorChip(ChangeColorChipReqDto req) {
        GitHubInfo gitHubInfo = findGitHubInfo(req);
        gitHubInfo.changeGrassColor(req.getColorCode());
    }

    private GitHubInfo findGitHubInfo(ChangeColorChipReqDto req) {
        return gitHubInfoRepository
                .findGitHubInfoByMemberId(req.getMemberId())
                .orElseThrow(NoGitHubInfoException::new);
    }
}
