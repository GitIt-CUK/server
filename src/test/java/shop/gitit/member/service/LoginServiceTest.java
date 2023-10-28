package shop.gitit.member.service;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.repository.RedisRepository;
import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.port.in.LoginUsecase;
import shop.gitit.member.service.port.out.OAuthWebClient;

@SpringBootTest
class LoginServiceTest {

    @Autowired private LoginUsecase loginUsecase;
    @Autowired private JoinService joinService;
    @Autowired private JwtTokenProvider jwtTokenProvider;
    @Autowired private MemberRepository memberRepository;
    @Autowired private RedisRepository redisRepository;
    @MockBean private OAuthWebClient oAuthWebClient;

    @Transactional
    @DisplayName("최초 깃허브로 로그인을 시도하면 회원가입을 진행하고 ATK, RTK를 생성한다. RTK는 Redis에 저장된다.")
    @Test
    void firstTimeLoginWithGithub() {
        // given
        String code = "github code";
        OAuthTokenResponse githubATK =
                OAuthTokenResponse.builder().accessToken("github access token").build();
        GithubUserInfo githubUserInfo =
                GithubUserInfo.builder()
                        .githubId("githubId")
                        .nickname("nickname")
                        .profileImg("imgURL")
                        .build();

        // when
        when(oAuthWebClient.getToken(code)).thenReturn(githubATK);
        when(oAuthWebClient.getUserProfile(githubATK)).thenReturn(githubUserInfo);

        // then
        assertThatNoException().isThrownBy(() -> loginUsecase.login(code));
    }
}
