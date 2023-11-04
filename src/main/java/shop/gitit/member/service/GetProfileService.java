package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.exception.NoGitHubInfoException;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.member.domain.Member;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.response.GetMemberProfileResDto;
import shop.gitit.member.service.port.in.GetProfileUsecase;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetProfileService implements GetProfileUsecase {

    private final MemberRepository memberRepository;
    private final GitHubInfoRepository gitHubInfoRepository;

    @Override
    public GetMemberProfileResDto getMemberProfile(Long memberId) {
        Member member =
                memberRepository
                        .findById(memberId)
                        .orElseThrow(() -> new NoMemberException("해당 회원이 존재하지 않습니다."));
        GitHubInfo gitHubInfo =
                gitHubInfoRepository
                        .findGitHubInfoByMemberId(memberId)
                        .orElseThrow(() -> new NoGitHubInfoException("깃허브 정보가 존재하지 않습니다."));
        return GetMemberProfileResDto.builder()
                .memberId(member.getId())
                .githubId(member.getProfile().getGithubId())
                .nickname(member.getProfile().getNickname())
                .profileImg(member.getProfile().getProfileImg())
                .commitCount(gitHubInfo.getCommitCount().getCount())
                .commitLimit(gitHubInfo.getTier().getLimit())
                .build();
    }
}
