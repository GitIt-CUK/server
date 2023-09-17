package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.memberprofile.MemberProfile;
import shop.gitit.member.exception.NoMemberException;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.response.GetMemberProfileResDto;
import shop.gitit.member.service.usecase.GetProfileUsecase;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetProfileService implements GetProfileUsecase {

    private final MemberRepository memberRepository;

    @Override
    public GetMemberProfileResDto getMemberProfile(Long memberId) {
        Member member =
                memberRepository
                        .findById(memberId)
                        .orElseThrow(() -> new NoMemberException("해당 회원이 존재하지 않습니다."));
        MemberProfile memberProfile = member.getProfile();
        return GetMemberProfileResDto.builder()
                .githubId(memberProfile.getGithubId())
                .nickname(memberProfile.getNickname())
                .build();
    }
}
