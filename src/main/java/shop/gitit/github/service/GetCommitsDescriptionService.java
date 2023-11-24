package shop.gitit.github.service;

import lombok.RequiredArgsConstructor;
import shop.gitit.github.service.dto.response.GetCommitsDescriptionResDto;
import shop.gitit.github.service.port.in.GetCommitsDescriptionUsecase;
import shop.gitit.github.service.port.out.GetCommitDescriptionPort;
import shop.gitit.member.domain.Member;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;

@RequiredArgsConstructor
public class GetCommitsDescriptionService implements GetCommitsDescriptionUsecase {

    private final GetCommitDescriptionPort getCommitDescriptionPort;
    private final MemberRepository memberRepository;

    @Override
    public GetCommitsDescriptionResDto getCommitsDescription(Long memberId) {
        Member member = memberRepository.findById(memberId).orElseThrow(NoMemberException::new);
        String githubId = member.getProfile().getGithubId();
        return getCommitDescriptionPort.getCommitDescription(githubId);
    }
}
