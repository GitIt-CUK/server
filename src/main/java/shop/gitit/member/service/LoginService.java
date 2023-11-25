package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.core.util.jwt.dto.JwtToken;
import shop.gitit.member.domain.Member;
import shop.gitit.member.domain.RefreshToken;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.repository.RedisRepository;
import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.dto.response.LoginResDto;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.port.in.JoinUsecase;
import shop.gitit.member.service.port.in.LoginUsecase;
import shop.gitit.member.service.port.out.OAuthWebClient;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements LoginUsecase {

    private final JoinUsecase joinUsecase;
    private final JwtTokenProvider jwtTokenProvider;
    private final OAuthWebClient oAuthWebClient;
    private final MemberRepository memberRepository;
    private final RedisRepository redisRepository;

    @Override
    public LoginResDto login(String code) {
        OAuthTokenResponse tokenResponse = oAuthWebClient.getToken(code);
        GithubUserInfo githubUserInfo = oAuthWebClient.getUserProfile(tokenResponse);
        Member member = joinUsecase.join(githubUserInfo);
        JwtToken jwtToken = jwtTokenProvider.createToken(member.getId());
        redisRepository.save(
                RefreshToken.builder()
                        .memberId(member.getId())
                        .token(jwtToken.getRefreshToken())
                        .build());
        return LoginResDto.builder()
                .memberId(member.getId())
                .nickname(member.getProfile().getNickname())
                .refreshToken(jwtToken.getRefreshToken())
                .accessToken(jwtToken.getAccessToken())
                .build();
    }
}
