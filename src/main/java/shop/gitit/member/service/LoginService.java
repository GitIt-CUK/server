package shop.gitit.member.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.gitit.core.util.jwt.JwtTokenProvider;
import shop.gitit.core.util.jwt.dto.JwtToken;
import shop.gitit.member.domain.Member;
import shop.gitit.member.repository.MemberRepository;
import shop.gitit.member.service.dto.response.GithubUserInfo;
import shop.gitit.member.service.dto.response.LoginResDto;
import shop.gitit.member.service.dto.response.OAuthTokenResponse;
import shop.gitit.member.service.port.in.LoginUsecase;
import shop.gitit.member.service.port.out.OAuthService;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class LoginService implements LoginUsecase {

    private final OAuthService oAuthService;
    private final JoinService joinService;
    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResDto login(String code) {
        OAuthTokenResponse tokenResponse = oAuthService.getToken(code);
        GithubUserInfo githubUserInfo = oAuthService.getUserProfile(tokenResponse);
        Member member = memberRepository.findByGithubId(githubUserInfo.getGithubId());
        if (member == null) {
            member = joinService.join(githubUserInfo);
        }
        if(!member.getProfile().getProfileImg().equals(githubUserInfo.getProfileImg())){
            member.getProfile()
                    .updateProfileImg(
                            githubUserInfo.getProfileImg());
        }
        JwtToken jwtToken = jwtTokenProvider.createToken(member.getId());
        // TODO refresh token 저장
        return LoginResDto.builder()
                .memberId(member.getId())
                .refreshToken(jwtToken.getRefreshToken())
                .accessToken(jwtToken.getAccessToken())
                .build();
    }
}
