package shop.gitit.member.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import shop.gitit.github.domain.GitHubInfo;
import shop.gitit.github.repository.GitHubInfoRepository;
import shop.gitit.github.support.githubinfo.GitHubInfoFixture;
import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.support.member.MemberFixture;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.response.GetMemberProfileResDto;
import shop.gitit.member.service.port.in.GetProfileUsecase;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {GetProfileService.class})
class GetProfileServiceTest {

    @Autowired private GetProfileUsecase getProfileUsecase;
    @MockBean private MemberRepository memberRepository;
    @MockBean private GitHubInfoRepository gitHubInfoRepository;

    @DisplayName("getProfile 메서드는 프로필을 조회한다.")
    @Test
    void getProfile() {
        // given
        Member member = MemberFixture.getMember();
        GitHubInfo gitHubInfo = GitHubInfoFixture.getGitHubInfo(1L);

        // when
        when(memberRepository.findById(anyLong())).thenReturn(Optional.ofNullable(member));
        when(gitHubInfoRepository.findGitHubInfoByMemberId(anyLong()))
                .thenReturn(Optional.ofNullable(gitHubInfo));
        GetMemberProfileResDto result = getProfileUsecase.getMemberProfile(1L);

        // then
        assertThat(result)
                .extracting(
                        GetMemberProfileResDto::getGithubId,
                        GetMemberProfileResDto::getNickname,
                        GetMemberProfileResDto::getProfileImg,
                        GetMemberProfileResDto::getCommitCount,
                        GetMemberProfileResDto::getCommitLimit)
                .contains(
                        member.getProfile().getGithubId(),
                        member.getProfile().getNickname(),
                        member.getProfile().getProfileImg(),
                        gitHubInfo.getCommitCount().getCount(),
                        gitHubInfo.getTier().getLimit());
    }
}
