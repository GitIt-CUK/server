package shop.gitit.member.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.memberprofile.MemberProfile;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.port.in.JoinUsecase;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinService implements JoinUsecase {

    private final MemberRepository memberRepository;
    private final String MEMBER = "MEMBER";

    public Member join(GithubUserInfo githubUserInfo) {
        Member member =
                Member.builder()
                        .profile(
                                MemberProfile.builder()
                                        .profileImg(githubUserInfo.getProfileImg())
                                        .githubId(githubUserInfo.getGithubId())
                                        .nickname(githubUserInfo.getGithubId())
                                        .build())
                        .authorities(List.of(MEMBER))
                        .build();
        return memberRepository.save(member);
    }
}
